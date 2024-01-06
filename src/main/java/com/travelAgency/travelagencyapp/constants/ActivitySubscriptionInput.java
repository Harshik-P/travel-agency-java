package com.travelAgency.travelagencyapp.constants;

public class ActivitySubscriptionInput {
    private String phoneNumber;
    private String packageId;
    private String activityId;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPackageId() {
        return packageId;
    }
    public String getActivityId() {
        return activityId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
