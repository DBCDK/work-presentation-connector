/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation;

import dk.dbc.httpclient.HttpClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.client.Client;

/**
 * WorkPresentationConnector factory
 * <p>
 * Synopsis:
 * </p>
 * <pre>
 *    // New instance
 *    WorkPresentationConnector connector = WorkPresentationConnectorFactory.create("http://opensearch-service");
 *
 *    // Singleton instance in CDI enabled environment
 *    {@literal @}Inject
 *    WorkPresentationConnectorFactory factory;
 *    ...
 *    WorkPresentationConnector connector = factory.getInstance();
 *
 *    // or simply
 *    {@literal @}Inject
 *    WorkPresentationConnector connector;
 * </pre>
 * <p>
 * The CDI case depends on the work-presentation service base-url being defined as
 * the value of either a system property or environment variable
 * named WORK_PRESENTATION_SERVICE_URL.
 * </p>
 */
@ApplicationScoped
public class WorkPresentationConnectorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkPresentationConnectorFactory.class);

    public static WorkPresentationConnector create(String baseUrl) throws WorkPresentationConnectorException {
        final Client client = HttpClient.newClient(new ClientConfig()
                .register(new JacksonFeature()));
        LOGGER.info("Creating WorkPresentationConnector for: {}", baseUrl);
        return new WorkPresentationConnector(client, baseUrl);
    }

    @Inject
    @ConfigProperty(name = "WORK_PRESENTATION_SERVICE_URL")
    private String baseUrl;

    WorkPresentationConnector workPresentationConnector;

    @PostConstruct
    public void initializeConnector() {
        try {
            workPresentationConnector = dk.dbc.opensearch.workpresentation.WorkPresentationConnectorFactory.create(baseUrl);
        } catch (WorkPresentationConnectorException e) {
            throw new IllegalStateException(e);
        }
    }

    @Produces
    public WorkPresentationConnector getInstance() {
        return workPresentationConnector;
    }

    @PreDestroy
    public void tearDownConnector() {
        workPresentationConnector.close();
    }
}
