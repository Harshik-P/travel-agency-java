package com.travelAgency.travelagencyapp.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String activityId;
    private String name;
    private String description;
    private int cost;
    private int capacity;

    public int getCost() {
        return cost;
    }
    public int getCapacity() {
        return capacity;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
