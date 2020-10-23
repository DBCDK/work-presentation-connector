/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation;

public class WorkPresentationConnectorException extends Exception {
    public WorkPresentationConnectorException(String message) {
        super(message);
    }

    public WorkPresentationConnectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
