package com.travelAgency.travelagencyapp.controller;

import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/getAllPassengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/getPassengerById/{id}")
    public Map<String, Object> getPassengerById(@PathVariable String id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping("/addPassenger")
    public Passenger createPassenger(@RequestBody Passenger newPassenger) {
        return passengerService.createPassenger(newPassenger);
    }
}
