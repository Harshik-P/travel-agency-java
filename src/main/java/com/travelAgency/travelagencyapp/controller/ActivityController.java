package com.travelAgency.travelagencyapp.controller;

import com.travelAgency.travelagencyapp.collections.Activity;
import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.constants.ActivitySubscriptionInput;
import com.travelAgency.travelagencyapp.service.ActivityService;
import com.travelAgency.travelagencyapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/getAllActivities")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/getAvailableActivities")
    public List<Activity> getAvailableActivities() {
        return activityService.getAvailableActivities();
    }

    @PostMapping("/addActivity")
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }
}
