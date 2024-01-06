package com.travelAgency.travelagencyapp.controller;

import com.travelAgency.travelagencyapp.collections.Destination;
import com.travelAgency.travelagencyapp.constants.DestinationInput;
import com.travelAgency.travelagencyapp.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/getAllDestinations")
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }


    @PostMapping("/addDestination")
    public Destination createDestination(@RequestBody DestinationInput destination) {
        return destinationService.createDestination(destination);
    }
}
