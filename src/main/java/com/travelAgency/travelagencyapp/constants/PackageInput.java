package com.travelAgency.travelagencyapp.constants;

import java.util.List;

public class PackageInput {
    private String name;
    private int passengerCapacity;
    private List<String> itinerary;
    private List<String> passengers;

    public String getName() {
        return name;
    }
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    public List<String> getItinerary() {
        return itinerary;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItinerary(List<String> itinerary) {
        this.itinerary = itinerary;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
