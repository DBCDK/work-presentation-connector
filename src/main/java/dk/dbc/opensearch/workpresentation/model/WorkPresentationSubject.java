/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkPresentationSubject {

    public WorkPresentationSubject() {}

    private String value;

    private String type = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WorkPresentationSubject withValue(String value) {
        this.value = value;
        return this;
    }

    public WorkPresentationSubject withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WorkPresentationSubject that = (WorkPresentationSubject) o;
        return value.equals(that.value) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        return "WorkPresentationSubject{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
