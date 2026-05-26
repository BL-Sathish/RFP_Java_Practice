package com.day_30.io_stream;

public class InvoiceGenerator {

    // UC1 Calculate Fare
    public double calculateFare(Ride ride) {

        double fare =
                (ride.distance * ride.rideType.costPerKm)
                + (ride.time * ride.rideType.costPerMinute);

        return Math.max(fare,
                ride.rideType.minimumFare);
    }

    // UC2 Multiple Rides
    public InnvoiceSummary calculateFare(Ride[] rides) {

        double totalFare = 0;

        for (Ride ride : rides) {

            totalFare += calculateFare(ride);
        }

        return new InnvoiceSummary(
                rides.length,
                totalFare);
    }
}
