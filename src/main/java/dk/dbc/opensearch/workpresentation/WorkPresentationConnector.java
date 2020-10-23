/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation;

import dk.dbc.httpclient.FailSafeHttpClient;
import dk.dbc.httpclient.HttpGet;
import dk.dbc.invariant.InvariantUtil;

import dk.dbc.opensearch.workpresentation.model.WorkPresentationEntity;
import dk.dbc.opensearch.workpresentation.model.WorkPresentationResult;
import net.jodah.failsafe.RetryPolicy;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import dk.dbc.util.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WorkPresentatinConnector - WorkPresentation client
 * <p>
 * To use this class, you construct an instance, specifying a web resources client as well as
 * a base URL for the WorkPresentatin service endpoint you will be communicating with.
 * </p>
 * <p>
 * This class is thread safe, as long as the given web resources client remains thread safe.
 * </p>
 */
public class WorkPresentationConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkPresentationConnector.class);

    private static final RetryPolicy RETRY_POLICY = new RetryPolicy()
            .retryOn(Collections.singletonList(ProcessingException.class))
            .retryIf((Response response) ->
                    response.getStatus() == 404
                            || response.getStatus() == 500
                            || response.getStatus() == 502)
            .withDelay(5, TimeUnit.SECONDS)
            .withMaxRetries(3);

    private FailSafeHttpClient failSafeHttpClient;
    private String baseUrl;

    public WorkPresentationConnector(Client httpClient, String baseUrl) {
        this(FailSafeHttpClient.create(httpClient, RETRY_POLICY), baseUrl);
    }

    /**
     * Returns new instance with custom retry policy
     *
     * @param failSafeHttpClient web resources client with custom retry policy
     * @param baseUrl            base URL for record service endpoint
     * @throws WorkPresentationConnectorException on failure to create {@link dk.dbc.opensearch.workpresentation.WorkPresentationConnector}
     */
    public WorkPresentationConnector(FailSafeHttpClient failSafeHttpClient, String baseUrl) {
        this.failSafeHttpClient = InvariantUtil.checkNotNullOrThrow(
                failSafeHttpClient, "failSafeHttpClient");
        this.baseUrl = InvariantUtil.checkNotNullNotEmptyOrThrow(
                baseUrl, "baseUrl");
    }

    public WorkPresentationResult presentWorks(WorkPresentationQuery query) throws WorkPresentationConnectorException {
        final Stopwatch stopwatch = new Stopwatch();

        try {
            LOGGER.info("Present work request with query {}", query.build());

            final HttpGet httpGet = new HttpGet(failSafeHttpClient)
                    .withBaseUrl(baseUrl)
                    .withPathElements("work-presentation")
                    .withQueryParameter("profile", query.getProfile())
                    .withQueryParameter("agencyId", query.getAgencyId())
                    .withQueryParameter("workId", query.build());

            return sendGetRequest(httpGet);
        } catch(java.io.UnsupportedEncodingException exception) {
            LOGGER.error("Query contains characters that can not be url encoded: {}", exception);
            throw new WorkPresentationConnectorException("Query contains characters that can not be url encoded", exception);
        } finally {
            LOGGER.info("Request took {} ms", stopwatch.getElapsedTime(TimeUnit.MILLISECONDS));
        }
    }

    public void close() {
        failSafeHttpClient.getClient().close();
    }

    private WorkPresentationResult sendGetRequest(HttpGet httpGet) throws WorkPresentationConnectorException {
        LOGGER.info("Request with query: {}", httpGet.toString());

        final Response response = httpGet.execute();
        assertResponseStatus(response, Response.Status.OK);

        return readResponseEntity(response);
    }

    private WorkPresentationResult readResponseEntity(Response response) throws WorkPresentationConnectorException {

        final WorkPresentationEntity entity = response.readEntity(WorkPresentationEntity.class);
        if (entity == null) {
            throw new WorkPresentationConnectorException("Work-presentation service returned with null-valued entity");
        }
        return entity.getResult();
    }

    private void assertResponseStatus(Response response, Response.Status expectedStatus)
            throws WorkPresentationUnexpectedStatusCodeException {
        final Response.Status actualStatus =
                Response.Status.fromStatusCode(response.getStatus());
        if (actualStatus != expectedStatus) {
            throw new WorkPresentationUnexpectedStatusCodeException(
                    String.format("Work-presentation service returned with unexpected status code: %s",
                            actualStatus), actualStatus.getStatusCode());
        }
    }
}