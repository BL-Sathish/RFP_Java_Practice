package com.day_29.io_stream;

public class CSVStateCensus {

    private String State;
    private long Population;
    private long AreaInSqKm;
    private long DensityPerSqKm;

    public String getState() {
        return State;
    }

    public long getPopulation() {
        return Population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public long getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    @Override
    public String toString() {

        return "State = " + State +
                ", Population = " + Population;
    }
}
