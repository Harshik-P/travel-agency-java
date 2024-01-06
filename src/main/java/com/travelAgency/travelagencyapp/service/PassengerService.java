package com.travelAgency.travelagencyapp.service;

import com.travelAgency.travelagencyapp.collections.*;
import com.travelAgency.travelagencyapp.constants.PassengerPrivilege;
import com.travelAgency.travelagencyapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Map<String, Object> getPassengerById(String passengerId) {
        Passenger passenger = passengerRepository.findByPassengerId(passengerId);

        List<Subscription> subscriptions = subscriptionRepository.findByPassengerId(passengerId);
        List<String> activityIds = new ArrayList<>();

        for(Subscription subscription: subscriptions) {
            activityIds.add(subscription.getActivityId());
        }

        Map<String, Object> activityDetails = new HashMap<>();
        for(String activityId: activityIds) {
            Activity fetchedActivity = activityRepository.findByActivityId(activityId);
            Destination destination = destinationRepository.findByActivitiesContains(activityId);
            String destinationName = destination.getName();
            Subscription subscrip = subscriptionRepository.findByActivityIdAndPassengerId(activityId, passengerId);
            double creditsPaidForActivity = subscrip.getCreditsPaid();

            activityDetails.put("destinationName", destinationName);
            activityDetails.put("creditsPaidForActivity", creditsPaidForActivity);
            activityDetails.put("activity", fetchedActivity);
        }

        Map<String, Object> passengerDetails = new HashMap<>();
        passengerDetails.put("name", passenger.getName());
        passengerDetails.put("phoneNumber", passenger.getPhoneNumber());
        passengerDetails.put("balance", passenger.getCredits());
        passengerDetails.put("activitiesSubscribed", activityDetails);

        return passengerDetails;
    }

    public Passenger createPassenger(Passenger newPassenger) {
        PassengerPrivilege passengerPrivilege = newPassenger.getPassengerPrivilege();
        if(passengerPrivilege != PassengerPrivilege.PREMIUM) {
            newPassenger.setCredits(100);
        }
        return passengerRepository.insert(newPassenger);
    }
}
