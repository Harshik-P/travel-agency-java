package com.travelAgency.travelagencyapp.repository;

import com.travelAgency.travelagencyapp.collections.Destination;
import com.travelAgency.travelagencyapp.collections.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
    Passenger findByPassengerId(String passengerId);
    Passenger findByPhoneNumber(String phoneNumber);
}
