package com.travelAgency.travelagencyapp;

import com.travelAgency.travelagencyapp.collections.Activity;
import com.travelAgency.travelagencyapp.collections.Destination;
import com.travelAgency.travelagencyapp.collections.Passenger;
import com.travelAgency.travelagencyapp.collections.TravelPackage;
import com.travelAgency.travelagencyapp.constants.ActivitySubscriptionInput;
import com.travelAgency.travelagencyapp.constants.DestinationInput;
import com.travelAgency.travelagencyapp.constants.PackageInput;
import com.travelAgency.travelagencyapp.constants.PassengerPrivilege;
import com.travelAgency.travelagencyapp.repository.ActivityRepository;
import com.travelAgency.travelagencyapp.repository.DestinationRepository;
import com.travelAgency.travelagencyapp.repository.PackageRepository;
import com.travelAgency.travelagencyapp.repository.PassengerRepository;
import com.travelAgency.travelagencyapp.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TravelAgencyAppApplicationTests {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerService passengerService;

    @Test
    void addPassengers() {
        Passenger passenger1 = new Passenger();
        passenger1.setName("Test User 4");
        passenger1.setPhoneNumber("9000000004");
        passenger1.setPassengerPrivilege(PassengerPrivilege.PREMIUM);

        Passenger passenger2 = new Passenger();
        passenger2.setName("Test User 5");
        passenger2.setPhoneNumber("9000000005");
        passenger2.setPassengerPrivilege(PassengerPrivilege.GOLD);

        Passenger passenger3 = new Passenger();
        passenger3.setName("Test User 6");
        passenger3.setPhoneNumber("9000000006");
        passenger3.setPassengerPrivilege(PassengerPrivilege.STANDARD);

        Passenger createdPassenger1 = passengerService.createPassenger(passenger1);
        Passenger createdPassenger2 = passengerService.createPassenger(passenger2);
        Passenger createdPassenger3 = passengerService.createPassenger(passenger3);

        assertEquals(createdPassenger1.getName(), "Test User 4");
        assertEquals(createdPassenger1.getPhoneNumber(), "9000000004");
        assertEquals(createdPassenger1.getPassengerPrivilege(), PassengerPrivilege.PREMIUM);
        assertEquals(createdPassenger1.getCredits(), 0);

        assertEquals(createdPassenger2.getName(), "Test User 5");
        assertEquals(createdPassenger2.getPhoneNumber(), "9000000005");
        assertEquals(createdPassenger2.getPassengerPrivilege(), PassengerPrivilege.GOLD);
        assertEquals(createdPassenger2.getCredits(), 100);

        assertEquals(createdPassenger3.getName(), "Test User 6");
        assertEquals(createdPassenger3.getPhoneNumber(), "9000000006");
        assertEquals(createdPassenger3.getPassengerPrivilege(), PassengerPrivilege.STANDARD);
        assertEquals(createdPassenger3.getCredits(), 100);
    }

	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ActivityService activityService;

	@Test
	void addActivity() {
		Activity activity1 = new Activity();
		activity1.setActivityId("eagle-view-point");
		activity1.setName("Eagle View Point");
		activity1.setDescription("Beautiful view point");
		activity1.setCost(20);
		activity1.setCapacity(20);

		Activity activity2 = new Activity();
		activity2.setActivityId("botanical-garden");
		activity2.setName("Botanical garden");
		activity2.setDescription("Stroll around mesmerising botanical garden");
		activity2.setCost(50);
		activity2.setCapacity(20);

		Activity createdActivity1 = activityService.createActivity(activity1);
		Activity createdActivity2 = activityService.createActivity(activity2);

		assertNotNull(createdActivity1);
		assertNotNull(createdActivity1.getId());
		assertNotNull(createdActivity2);
		assertNotNull(createdActivity2.getId());

		Activity retrievedActivity1 = activityRepository.findById(createdActivity1.getId()).orElse(null);
		Activity retrievedActivity2 = activityRepository.findById(createdActivity2.getId()).orElse(null);

		assertNotNull(retrievedActivity1);
		assertEquals("eagle-view-point", retrievedActivity1.getActivityId());
		assertEquals("Eagle View Point", retrievedActivity1.getName());
		assertEquals("Beautiful view point", retrievedActivity1.getDescription());
		assertEquals(20, retrievedActivity1.getCost());
		assertEquals(20, retrievedActivity1.getCapacity());

		assertNotNull(retrievedActivity2);
		assertEquals("botanical-garden", retrievedActivity2.getActivityId());
		assertEquals("Botanical garden", retrievedActivity2.getName());
		assertEquals("Stroll around mesmerising botanical garden", retrievedActivity2.getDescription());
		assertEquals(50, retrievedActivity2.getCost());
		assertEquals(20, retrievedActivity2.getCapacity());
	}

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private DestinationService destinationService;

	@Test
	public void testCreateDestination() {
		DestinationInput destination = new DestinationInput();
		destination.setDestinationId("ooty");
		destination.setName("Ooty");
		List<String> activityList = new ArrayList<>();
		activityList.add("eagle-view-point");
		activityList.add("botanical-garden");
		destination.setActivities(activityList);

		Destination createdDestination = destinationService.createDestination(destination);

		assertNotNull(createdDestination);
		assertNotNull(createdDestination.getId());

		List<Map<String, Object>> expectedActivityList = new ArrayList<>();

		Map<String, Object> result1 = new HashMap<>();
		result1.put("cost", 50);
		result1.put("name", "Botanical garden");
		result1.put("activityId", "botanical-garden");
		result1.put("description", "Stroll around mesmerising botanical garden");
		result1.put("capacity", 20);

		Map<String, Object> result2 = new HashMap<>();
		result2.put("cost", 20);
		result2.put("name", "Eagle View Point");
		result2.put("activityId", "eagle-view-point");
		result2.put("description", "Beautiful view point");
		result2.put("capacity", 20);

		expectedActivityList.add(result1);
		expectedActivityList.add(result2);

		Destination retrievedDestination = destinationRepository.findById(createdDestination.getId()).orElse(null);
		assertNotNull(retrievedDestination);
		assertEquals("ooty", retrievedDestination.getDestinationId());
		assertEquals("Ooty", retrievedDestination.getName());

		List<Activity> actualActivityList = retrievedDestination.getActivities();

		assertEquals(expectedActivityList.size(), actualActivityList.size());

		for (Map<String, Object> expectedActivity : expectedActivityList) {
			boolean found = false;
			for (Activity actualActivity : actualActivityList) {
				if (expectedActivity.get("activityId").equals(actualActivity.getActivityId())
						&& expectedActivity.get("cost").equals(actualActivity.getCost())
						&& expectedActivity.get("name").equals(actualActivity.getName())
						&& expectedActivity.get("description").equals(actualActivity.getDescription())
						&& expectedActivity.get("capacity").equals(actualActivity.getCapacity())) {
					found = true;
					break;
				}
			}
			assertTrue(found, "Expected activity not found in retrieved activities");
		}
	}


	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private PackageService packageService;

	@Test
	public void testCreatePackage() {
		List<String> emptyList = new ArrayList<>();
		List<String> destinationsIds = new ArrayList<>();
		destinationsIds.add("ooty");


		PackageInput travelPackage = new PackageInput();
		travelPackage.setName("Tamil Nadu Package");
		travelPackage.setPassengerCapacity(30);
		travelPackage.setPassengers(emptyList);
		travelPackage.setItinerary(destinationsIds);

		TravelPackage createdPackage = packageService.createPackage(travelPackage);

		assertNotNull(createdPackage);
		assertNotNull(createdPackage.getPackageId());

		TravelPackage retrievedPackage = packageRepository.findById(createdPackage.getPackageId()).orElse(null);
		assertNotNull(retrievedPackage);
		assertEquals("Tamil Nadu Package", retrievedPackage.getName());
		assertEquals(30, retrievedPackage.getPassengerCapacity());
//		assertEquals("north-goa", retrievedPackage.getItinerary());
	}

	@Autowired
	private SubscriptionService subscriptionService;

    @Test
    public void subscribeToAnActivity() {
        ActivitySubscriptionInput subscribe = new ActivitySubscriptionInput();
        subscribe.setActivityId("botanical-garden");
        subscribe.setPackageId("6599cea0a059363e68615e05");   //Manually enter the id
        subscribe.setPhoneNumber("9000000005");

        ResponseEntity<String> subscription = subscriptionService.addPassengersToActivity(subscribe);

        assertEquals(subscription.getBody(), "Congratulations! You have successfully subscribe to this activity.");

        //Checking the credits
        Passenger passenger = passengerRepository.findByPhoneNumber("9000000005");
        double credits = passenger.getCredits();
        assertEquals(credits, 55);

        //Checking the capacity
        Activity activity = activityRepository.findByActivityId("beach-hopping");
        int capacity = activity.getCapacity();
        assertEquals(capacity, 19);

        //Check whether the passenger added to the passenger list of the respective package
        TravelPackage travelPackage = packageRepository.findByPackageId("6599cea0a059363e68615e05");
        List<Passenger> passengerList = travelPackage.getPassengers();

        for(Passenger passenger1: passengerList) {
            if(passenger1.getPhoneNumber() == "9000000005") {
                assertEquals(passenger1.getPhoneNumber(), "9000000005");
            }
        }
    }
}
