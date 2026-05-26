package com.day_30.io_stream;

public class Main {

    public static void main(String[] args) {

        // Create Repository
        RideRepository repository =
                new RideRepository();

        // Add Rides
        Ride[] rides = {

                new Ride(10, 15,
                        RideType.NORMAL),

                new Ride(5, 10,
                        RideType.PREMIUM),

                new Ride(2, 5,
                        RideType.NORMAL)
        };

        // Add User
        repository.addRides("USER101", rides);

        // Invoice Service
        InvoiceService service =
                new InvoiceService(repository);

        // Get Invoice
        InnvoiceSummary summary =
                service.getInvoice("USER101");

        System.out.println(summary);
    }
}
