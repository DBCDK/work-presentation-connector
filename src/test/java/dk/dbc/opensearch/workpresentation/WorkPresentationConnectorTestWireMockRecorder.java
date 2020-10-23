/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation;

public class WorkPresentationConnectorTestWireMockRecorder {
        /*
        Steps to reproduce wiremock recording:

        * Start standalone runner
            java -jar wiremock-standalone-{WIRE_MOCK_VERSION}.jar --proxy-all="{WORK_PRESENTATION_SERVICE_HOST}" --record-mappings --verbose

        * Run the main method of this class

        * Replace content of src/test/resources/{__files|mappings} with that produced by the standalone runner
     */
    public static void main(String[] args) throws Exception {
        WorkPresentationConnectorTest.connector = new WorkPresentationConnector(
                WorkPresentationConnectorTest.CLIENT, "http://localhost:8080");
        final WorkPresentationConnectorTest WorkPresentationConnectorTest = new WorkPresentationConnectorTest();
        recordGetApplicantRequests(WorkPresentationConnectorTest);
    }

    private static void recordGetApplicantRequests(WorkPresentationConnectorTest WorkPresentationConnectorTest)
            throws WorkPresentationConnectorException {
        WorkPresentationConnectorTest.testWorkPresentationResponse();
    }

}
