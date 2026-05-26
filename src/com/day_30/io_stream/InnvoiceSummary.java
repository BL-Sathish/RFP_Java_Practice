package com.day_30.io_stream;

public class InnvoiceSummary {

    private int numberOfRides;
    private double totalFare;
    private double averageFare;

    public InnvoiceSummary(int numberOfRides,
                          double totalFare) {

        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
        this.averageFare =
                totalFare / numberOfRides;
    }

    @Override
    public String toString() {

        return "Invoice Summary\n" +
                "------------------\n" +
                "Total Rides : " + numberOfRides + "\n" +
                "Total Fare  : ₹" + totalFare + "\n" +
                "Average Fare: ₹" + averageFare;
    }
}
