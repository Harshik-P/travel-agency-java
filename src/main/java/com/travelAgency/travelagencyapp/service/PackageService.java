package com.travelAgency.travelagencyapp.service;

import com.travelAgency.travelagencyapp.collections.Destination;
import com.travelAgency.travelagencyapp.collections.TravelPackage;
import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.constants.PackageInput;
import com.travelAgency.travelagencyapp.repository.DestinationRepository;
import com.travelAgency.travelagencyapp.repository.PackageRepository;
import com.travelAgency.travelagencyapp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public List<TravelPackage> getAllPackages() { return packageRepository.findAll(); }

    public TravelPackage getItineraryOfAPackage(String packageId) {
        return packageRepository.findByPackageId(packageId);
    }

    public Map<String, Object> getPassengersList(String packageId) {
        TravelPackage pack = packageRepository.findByPackageId(packageId);
        String packageName = pack.getName();
        int passengerCapacity = pack.getPassengerCapacity();
        List<Passenger> passengersEnrolled = pack.getPassengers();
        int passengersEnrolledCount = passengersEnrolled.size();

        List<Object> passengersArray = new ArrayList<>();
        for(Passenger passenger: passengersEnrolled) {
            Map<String, Object> passengersDetails = new HashMap<>();
            passengersDetails.put("name", passenger.getName());
            passengersDetails.put("phoneNumber", passenger.getPhoneNumber());
            passengersArray.add(passengersDetails);
        }

        Map<String, Object> passengersListMap = new HashMap<>();
        passengersListMap.put("packageName", packageName);
        passengersListMap.put("passengerCapacity", passengerCapacity);
        passengersListMap.put("enrolled_pasenger_count", passengersEnrolledCount);
        passengersListMap.put("passengerList", passengersArray);

        return passengersListMap;
    }

    public TravelPackage createNewPackage(TravelPackage pack) {
        return packageRepository.insert(pack);
    }

    public TravelPackage createPackage(PackageInput packageInput) {
        List<String> destinationIds = packageInput.getItinerary();
        List<String> passengerIds = packageInput.getPassengers();

        List<Destination> finalDestinations = new ArrayList<>();
        for(String destinationId: destinationIds) {
            Destination destination = destinationRepository.findByDestinationId(destinationId);
            if(destination != null) {
                finalDestinations.add(destination);
            }
        }

        List<Passenger> finalPassengers = new ArrayList<>();
        for(String passengerId: passengerIds) {
            Passenger passenger = passengerRepository.findByPassengerId(passengerId);
            if(passenger != null) {
                finalPassengers.add(passenger);
            }
        }

        TravelPackage finalPackage = new TravelPackage();
        finalPackage.setName(packageInput.getName());
        finalPackage.setPassengerCapacity(packageInput.getPassengerCapacity());
        finalPackage.setItinerary(finalDestinations);
        finalPackage.setPassengers(finalPassengers);

        return packageRepository.insert(finalPackage);
    }
}
