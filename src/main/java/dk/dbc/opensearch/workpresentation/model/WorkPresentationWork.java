/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkPresentationWork {

    public WorkPresentationWork() {}

    private String description;

    private String title;

    private String fullTitle;

    private String workId;

    private WorkPresentationSubject[] subjects;

    private WorkPresentationRecord[] records;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public WorkPresentationSubject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(WorkPresentationSubject[] subjects) {
        this.subjects = subjects;
    }

    public WorkPresentationRecord[] getRecords() {
        return records;
    }

    public void setRecords(WorkPresentationRecord[] records) {
        this.records = records;
    }

    public WorkPresentationWork withDescription(String description) {
        this.description = description;
        return this;
    }

    public WorkPresentationWork withTitle(String title) {
        this.title = title;
        return this;
    }

    public WorkPresentationWork withFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
        return this;
    }

    public WorkPresentationWork withWorkId(String workId) {
        this.workId = workId;
        return this;
    }

    public WorkPresentationWork withSubjects(WorkPresentationSubject[] subjects) {
        this.subjects = subjects;
        return this;
    }

    public WorkPresentationWork withRecords(WorkPresentationRecord[] records) {
        this.records = records;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WorkPresentationWork that = (WorkPresentationWork) o;
        return description.equals(that.description) &&
                title.equals(that.title) &&
                fullTitle.equals(that.fullTitle) &&
                workId.equals(that.workId) &&
                Arrays.equals(subjects, that.subjects) &&
                Arrays.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(description, title, fullTitle, workId);
        result = 31 * result + Arrays.hashCode(subjects);
        result = 31 * result + Arrays.hashCode(records);
        return result;
    }
}
