package com.keralty.integraciones.organization.getorganization.request;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class GetOrganizationRequest {
    private String resourceType;
    private String id;
    private String type;
    private List<Entry> entry;

    @JsonProperty("resourceType")
    public String getResourceType() { return resourceType; }
    @JsonProperty("resourceType")
    public void setResourceType(String value) { this.resourceType = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("entry")
    public List<Entry> getEntry() { return entry; }
    @JsonProperty("entry")
    public void setEntry(List<Entry> value) { this.entry = value; }
    
    public void addEntry(Entry entry) {
    	this.entry.add(entry);
    }
}
