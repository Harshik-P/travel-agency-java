package com.travelAgency.travelagencyapp.service;

import com.travelAgency.travelagencyapp.collections.Activity;
import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.collections.Subscription;
import com.travelAgency.travelagencyapp.collections.TravelPackage;
import com.travelAgency.travelagencyapp.constants.ActivitySubscriptionInput;
import com.travelAgency.travelagencyapp.constants.PassengerPrivilege;
import com.travelAgency.travelagencyapp.repository.ActivityRepository;
import com.travelAgency.travelagencyapp.repository.PackageRepository;
import com.travelAgency.travelagencyapp.repository.PassengerRepository;
import com.travelAgency.travelagencyapp.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private PackageRepository packageRepository;

    public ResponseEntity<String> addPassengersToActivity(ActivitySubscriptionInput activitySubscriptionInput) {
        String phoneNumber = activitySubscriptionInput.getPhoneNumber();
        String packageId = activitySubscriptionInput.getPackageId();
        String activityId = activitySubscriptionInput.getActivityId();

        Passenger passenger = passengerRepository.findByPhoneNumber(phoneNumber);
        if(passenger.getPassengerId().isEmpty()) {
            return ResponseEntity.badRequest().body("No passenger found with respective to given phoneNumber");
        }

        String passengerId = passenger.getPassengerId();

        Subscription subscription = subscriptionRepository.findByActivityIdAndPassengerId(activityId, passengerId);
        if(subscription == null) {
            Activity activity = activityRepository.findByActivityId(activityId);
            int capacity = activity.getCapacity();
            if(capacity>0) {
                int activityCost = activity.getCost();
                PassengerPrivilege passengerPrivilege = passenger.getPassengerPrivilege();
                double passengerCredits = passenger.getCredits();

                boolean addPassengerToActivity = false;
                double subscriptionCostPaid = 0;
                if(passengerPrivilege == PassengerPrivilege.PREMIUM) {
                    addPassengerToActivity = true;
                } else if(passengerPrivilege == PassengerPrivilege.GOLD) {
                    double discount = activityCost * 0.1;
                    double subscriptionCost = activityCost - discount;
                    if(passengerCredits >= subscriptionCost) {
                        addPassengerToActivity = true;
                        subscriptionCostPaid = subscriptionCost;
                    }
                } else if(passengerPrivilege == PassengerPrivilege.STANDARD) {
                    if(passengerCredits >= activityCost) {
                        addPassengerToActivity = true;
                        subscriptionCostPaid = activityCost;
                    }
                }

                if(addPassengerToActivity) {
                    Subscription newSubscription = new Subscription();
                    newSubscription.setActivityId(activityId);
                    newSubscription.setPassengerId(passengerId);
                    newSubscription.setCreditsPaid(subscriptionCostPaid);
                    subscriptionRepository.insert(newSubscription);

                    passenger.setCredits(passengerCredits - subscriptionCostPaid);
                    passengerRepository.save(passenger);

                    activity.setCapacity(activity.getCapacity() - 1);
                    activityRepository.save(activity);

                    TravelPackage parentPackage = packageRepository.findByPackageId(packageId);
                    List<Passenger> currentPassengers = parentPackage.getPassengers();

                    boolean isPassengerExists = false;
                    for(Passenger pass: currentPassengers) {
                        if(pass.getPassengerId() == passengerId) {
                            isPassengerExists = true;
                        }
                    }

                    if(!isPassengerExists) {
                        currentPassengers.add(passenger);
                    }

                    return ResponseEntity.ok("Congratulations! You have successfully subscribe to this activity.");
                }

            } else {
                return ResponseEntity.internalServerError().body("The activity's capacity limit has reached. Unable to subscribe you.");
            }
        }
        return ResponseEntity.internalServerError().body("Passenger already subscribed to this activity");
    }
}
