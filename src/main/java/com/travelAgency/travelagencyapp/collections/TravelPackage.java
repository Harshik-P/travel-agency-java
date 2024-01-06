package com.travelAgency.travelagencyapp.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "packages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackage {
    @Id
    private String packageId;
    private String name;
    private int passengerCapacity;
    @DocumentReference
    private List<Destination> itinerary;
    @DocumentReference
    private List<Passenger> passengers;


    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    public String getName() {
        return name;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String getPackageId() {
        return packageId;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setItinerary(List<Destination> itinerary) {
        this.itinerary = itinerary;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
