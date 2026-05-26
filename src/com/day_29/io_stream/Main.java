package com.day_29.io_stream;

public class Main {

    public static void main(String[] args) {

        StateCensusAnalyser analyser =
                new StateCensusAnalyser();

        try {

            int censusCount =
                    analyser.loadStateCensusData(
                            "csv/StateCensusData.csv");

            System.out.println(
                    "Census Records: " + censusCount);

            int codeCount =
                    analyser.loadStateCodeData(
                            "csv/StateCode.csv");

            System.out.println(
                    "State Code Records: " + codeCount);

        } catch (CensusAnalyserException e) {

            System.out.println(
                    "Exception: " + e.getMessage());

            System.out.println(
                    "Type: " + e.type);
        }
    }
}
