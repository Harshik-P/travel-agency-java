package com.travelAgency.travelagencyapp.repository;

import com.travelAgency.travelagencyapp.collections.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends MongoRepository<Destination, String> {
    Destination findByDestinationId(String destinationId);

    Destination findByActivitiesContains(String activityId);
}
