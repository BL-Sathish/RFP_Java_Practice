package com.day_30.io_stream;

import java.util.HashMap;

public class RideRepository {

    HashMap<String, Ride[]> userRides =
            new HashMap<>();

    // Add User Ride
    public void addRides(String userId,
                         Ride[] rides) {

        userRides.put(userId, rides);
    }

    // Get User Rides
    public Ride[] getRides(String userId) {

        return userRides.get(userId);
    }
}
