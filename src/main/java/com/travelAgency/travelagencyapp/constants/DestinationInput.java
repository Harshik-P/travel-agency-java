package com.travelAgency.travelagencyapp.constants;

import java.util.List;

public class DestinationInput {
    private String destinationId;
    private String name;
    private List<String> activities;

    public String getDestinationId() {
        return destinationId;
    }
    public String getName() {
        return name;
    }
    public List<String> getActivities() {
        return activities;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public void setName(String name) {
        this.name = name;
    }
}
