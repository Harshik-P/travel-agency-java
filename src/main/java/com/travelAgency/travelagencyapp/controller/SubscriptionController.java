package com.travelAgency.travelagencyapp.controller;

import com.travelAgency.travelagencyapp.constants.ActivitySubscriptionInput;
import com.travelAgency.travelagencyapp.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribeActivity")
    public ResponseEntity<String> subscribeActivity(@RequestBody ActivitySubscriptionInput activitySubscriptionInput) {
        return subscriptionService.addPassengersToActivity(activitySubscriptionInput);
    }
}
