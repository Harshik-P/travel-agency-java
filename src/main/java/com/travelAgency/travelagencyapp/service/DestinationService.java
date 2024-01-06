package com.travelAgency.travelagencyapp.service;

import com.travelAgency.travelagencyapp.collections.Activity;
import com.travelAgency.travelagencyapp.collections.Destination;
import com.travelAgency.travelagencyapp.constants.DestinationInput;
import com.travelAgency.travelagencyapp.repository.ActivityRepository;
import com.travelAgency.travelagencyapp.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination createDestination(DestinationInput destination) {
        List<String> associatedActivityIds = destination.getActivities();

        List<Activity> finalActivities = new ArrayList<>();
        for(String activityId: associatedActivityIds) {
            Activity activity = activityRepository.findByActivityId(activityId);
            if(activity != null) {
                finalActivities.add(activity);
            }
        }

        Destination finalDestination = new Destination();
        finalDestination.setDestinationId(destination.getDestinationId());
        finalDestination.setName(destination.getName());
        finalDestination.setActivities(finalActivities);

        return destinationRepository.insert(finalDestination);
    }
}
