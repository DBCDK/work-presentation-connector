/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */
package dk.dbc.opensearch.workpresentation;

import com.github.tomakehurst.wiremock.WireMockServer;
import dk.dbc.httpclient.HttpClient;

import dk.dbc.opensearch.workpresentation.model.WorkPresentationWork;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WorkPresentationConnectorTest {
    private static WireMockServer wireMockServer;
    private static String wireMockHost;

    final static Client CLIENT = HttpClient.newClient(new ClientConfig()
            .register(new JacksonFeature()));
    static WorkPresentationConnector connector;

    @BeforeAll
    static void startWireMockServer() {

        wireMockServer = new WireMockServer(options().dynamicPort()
                .dynamicHttpsPort());
        wireMockServer.start();
        wireMockHost = "http://localhost:" + wireMockServer.port();
        configureFor("localhost", wireMockServer.port());
    }

    @BeforeAll
    static void setConnector() throws WorkPresentationConnectorException {
        connector = new WorkPresentationConnector(CLIENT, wireMockHost);
    }

    @AfterAll
    static void stopWireMockServer() {
        wireMockServer.stop();
    }

    @Test
    public void testWorkPresentationResponse() throws WorkPresentationConnectorException {

        try {
            WorkPresentationWork result = connector
                    .presentWorks(new WorkPresentationQuery()
                            .withManifestation("24699773"));
        }
        catch(WorkPresentationConnectorException connectorException) {
            throw connectorException;
        }
    }

    @Test
    public void testWorkPresentationWorkInfo() throws WorkPresentationConnectorException {

        try {
            WorkPresentationWork result = connector
                    .presentWorks(new WorkPresentationQuery()
                            .withManifestation("24699773"));

            assertThat(result.getDescription().contains("karismatiske fødselslæge"), is(true));
            assertThat(result.getFullTitle(), is("Den lukkede bog : roman"));
            assertThat(result.getTitle(), is("Den lukkede bog"));
            assertThat(result.getWorkId(), is("work-of:870970-basis:24699773"));
        }
        catch(WorkPresentationConnectorException connectorException) {
            throw connectorException;
        }
    }
}