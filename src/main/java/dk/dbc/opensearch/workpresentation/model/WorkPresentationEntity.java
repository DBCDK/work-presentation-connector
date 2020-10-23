/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkPresentationEntity {

    public WorkPresentationEntity() {}

    private WorkPresentationResult result;

    public WorkPresentationResult getResult() {
        return result;
    }

    public void setResult(WorkPresentationResult result) {
        this.result = result;
    }

    // Todo: Build model
}
