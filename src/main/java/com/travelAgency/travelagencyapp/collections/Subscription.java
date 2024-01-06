package com.travelAgency.travelagencyapp.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private String activityId;
    private String passengerId;
    private double creditsPaid;

    public String getActivityId() {
        return activityId;
    }

    public double getCreditsPaid() {
        return creditsPaid;
    }
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
    public void setCreditsPaid(double creditsPaid) {
        this.creditsPaid = creditsPaid;
    }
}
