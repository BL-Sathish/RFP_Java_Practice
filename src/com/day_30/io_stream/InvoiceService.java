package com.day_30.io_stream;

public class InvoiceService {

    private RideRepository repository;
    private InvoiceGenerator generator;

    public InvoiceService(RideRepository repository) {

        this.repository = repository;
        this.generator = new InvoiceGenerator();
    }

    // UC4 Get Invoice By User ID
    public InnvoiceSummary getInvoice(String userId) {

        Ride[] rides =
                repository.getRides(userId);

        return generator.calculateFare(rides);
    }
}
