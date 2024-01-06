package com.travelAgency.travelagencyapp.service;

import com.travelAgency.travelagencyapp.collections.*;
import com.travelAgency.travelagencyapp.constants.ActivitySubscriptionInput;
import com.travelAgency.travelagencyapp.constants.PassengerPrivilege;
import com.travelAgency.travelagencyapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getAvailableActivities() {
        return activityRepository.findByCapacityGreaterThan(0);
    }
    public Activity createActivity(Activity activity) {
        return activityRepository.insert(activity);
    }
}
