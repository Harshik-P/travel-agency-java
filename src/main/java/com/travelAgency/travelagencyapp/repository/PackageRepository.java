package com.travelAgency.travelagencyapp.repository;

import com.travelAgency.travelagencyapp.collections.TravelPackage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends MongoRepository<TravelPackage, String> {
    TravelPackage findByPackageId(String packageId);
}
