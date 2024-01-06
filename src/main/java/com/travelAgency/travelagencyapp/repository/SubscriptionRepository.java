package com.travelAgency.travelagencyapp.repository;

import com.travelAgency.travelagencyapp.collections.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    Subscription findByActivityIdAndPassengerId(String activityId, String passengerId);
    List<Subscription> findByPassengerId(String passengerId);
}
