package com.travelAgency.travelagencyapp.controller;

import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.collections.TravelPackage;
import com.travelAgency.travelagencyapp.constants.PackageInput;
import com.travelAgency.travelagencyapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping("/getAllPackages")
    public List<TravelPackage> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/getItinerary/{packageId}")
    public TravelPackage getItineraryOfAPackage(@PathVariable String packageId) {
        return packageService.getItineraryOfAPackage(packageId);
    }

    @GetMapping("/getPassengersList/{packageId}")
    public Map<String, Object> getPassengersList(@PathVariable String packageId) {
        return packageService.getPassengersList(packageId);
    }

    @PostMapping("/addNewPackage")
    public TravelPackage createNewPackage(@RequestBody TravelPackage pack) {
        return packageService.createNewPackage(pack);
    }

    @PostMapping("/addPackage")
    public TravelPackage createPackage(@RequestBody PackageInput packageInput) {
        return packageService.createPackage(packageInput);
    }
}
