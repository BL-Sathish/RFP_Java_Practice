package com.day_29.io_stream;

public class CSVStateCode {

    private int SrNo;
    private String StateName;
    private int TIN;
    private String StateCode;

    public String getStateName() {
        return StateName;
    }

    public String getStateCode() {
        return StateCode;
    }

    @Override
    public String toString() {

        return "State = " + StateName +
                ", Code = " + StateCode;
    }
}