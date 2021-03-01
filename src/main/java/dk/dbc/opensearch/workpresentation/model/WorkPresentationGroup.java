package dk.dbc.opensearch.workpresentation.model;

import java.util.Arrays;
import java.util.List;

public class WorkPresentationGroup {

    private WorkPresentationRecord[] records;

    public WorkPresentationRecord[] getRecords() {
        return records;
    }

    public void setRecords(WorkPresentationRecord[] records) {
        this.records = records;
    }

    public WorkPresentationGroup withRecords(WorkPresentationRecord[] records) {
        this.records = records;
        return this;
    }

    @Override
    public String toString() {
        return "WorkPresentationGroup{" +
                "records=" + Arrays.toString(records) +
                '}';
    }
}
