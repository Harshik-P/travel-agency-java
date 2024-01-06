package com.travelAgency.travelagencyapp.collections;

import com.travelAgency.travelagencyapp.constants.PassengerPrivilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Optional;

@Document(collection = "passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    private String passengerId;
    private String name;
    @Indexed(unique = true)
    private String phoneNumber;
    private PassengerPrivilege passengerPrivilege;
    private double credits;


    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassengerId() {
        return passengerId;
    }
    public PassengerPrivilege getPassengerPrivilege() {
        return passengerPrivilege;
    }
    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassengerPrivilege(PassengerPrivilege passengerPrivilege) {
        this.passengerPrivilege = passengerPrivilege;
    }
}
