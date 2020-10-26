/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkPresentationRecord {

    public WorkPresentationRecord() {}

    private String id;

    private String[] types;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public WorkPresentationRecord withId(String id) {
        this.id = id;
        return this;
    }

    public WorkPresentationRecord withTypes(String[] types) {
        this.types = types;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WorkPresentationRecord that = (WorkPresentationRecord) o;
        return id.equals(that.id) &&
                Arrays.equals(types, that.types);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(types);
        return result;
    }
}
