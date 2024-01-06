package com.travelAgency.travelagencyapp.repository;

import com.travelAgency.travelagencyapp.collections.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    Activity findByActivityId(String activityId);

    List<Activity> findByCapacityGreaterThan(int capacity);
}
