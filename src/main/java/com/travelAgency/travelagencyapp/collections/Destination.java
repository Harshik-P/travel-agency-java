package com.travelAgency.travelagencyapp.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "destinations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destination {
    @Id
    private String id;
    @Indexed(unique = true)
    private String destinationId;
    private String name;
    @DocumentReference
    private List<Activity> activities;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDestinationId() {
        return destinationId;
    }
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
