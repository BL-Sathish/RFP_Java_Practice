package com.day_30.io_stream;

public enum RideType {

    NORMAL(10, 1, 5),
    PREMIUM(15, 2, 20);

    public final double costPerKm;
    public final double costPerMinute;
    public final double minimumFare;

    RideType(double costPerKm,
             double costPerMinute,
             double minimumFare) {

        this.costPerKm = costPerKm;
        this.costPerMinute = costPerMinute;
        this.minimumFare = minimumFare;
    }
}
