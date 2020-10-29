/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.opensearch.workpresentation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WorkPresentationQuery {

    private String agencyId = "870970";

    private String agencyNamedIdentifier = "basis";

    private String manifestation;

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getManifestation() {
        return manifestation;
    }

    public void setManifestation(String manifestation) {
        this.manifestation = manifestation;
    }

    public String getAgencyNamedIdentifier() {
        return agencyNamedIdentifier;
    }

    public void setAgencyNamedIdentifier(String agencyNamedIdentifier) {
        this.agencyNamedIdentifier = agencyNamedIdentifier;
    }

    public WorkPresentationQuery withManifestation(String manifestation) {
        this.manifestation = manifestation;
        return this;
    }

    public WorkPresentationQuery withAgencyId(String agencyId) {
        this.agencyId = agencyId;
        return this;
    }

    public WorkPresentationQuery withAgencyNamedIdentifier(String identifier) {
        this.agencyNamedIdentifier = identifier;
        return this;
    }

    public String build() throws UnsupportedEncodingException {

        String query = String.format("work-of:%s-%s:%s", agencyId, agencyNamedIdentifier, manifestation);
        return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
    }
}
