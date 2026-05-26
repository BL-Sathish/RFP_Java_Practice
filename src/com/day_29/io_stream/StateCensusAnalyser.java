package com.day_29.io_stream;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.*;

public class StateCensusAnalyser {

    // Load Census CSV
    public int loadStateCensusData(String csvFilePath)
            throws CensusAnalyserException {

        try {

            if (!csvFilePath.endsWith(".csv")) {

                throw new CensusAnalyserException(
                        "Incorrect File Type",
                        CensusAnalyserException.ExceptionType
                                .INCORRECT_FILE_TYPE);
            }

            FileReader reader =
                    new FileReader(csvFilePath);

            CsvToBean<CSVStateCensus> csvToBean =
                    new CsvToBeanBuilder<CSVStateCensus>(reader)
                            .withType(CSVStateCensus.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            Iterator<CSVStateCensus> iterator =
                    csvToBean.iterator();

            int count = 0;

            while (iterator.hasNext()) {

                iterator.next();
                count++;
            }

            return count;

        } catch (java.io.FileNotFoundException e) {

            throw new CensusAnalyserException(
                    "File Not Found",
                    CensusAnalyserException.ExceptionType
                            .NO_SUCH_FILE);

        } catch (RuntimeException e) {

            throw new CensusAnalyserException(
                    "Incorrect Delimiter/Header",
                    CensusAnalyserException.ExceptionType
                            .INCORRECT_DELIMITER);

        } catch (Exception e) {

            throw new CensusAnalyserException(
                    "Error Loading Census Data",
                    CensusAnalyserException.ExceptionType
                            .INCORRECT_HEADER);
        }
    }

    // Load State Code CSV
    public int loadStateCodeData(String csvFilePath)
            throws CensusAnalyserException {

        try {

            if (!csvFilePath.endsWith(".csv")) {

                throw new CensusAnalyserException(
                        "Incorrect File Type",
                        CensusAnalyserException.ExceptionType
                                .INCORRECT_FILE_TYPE);
            }

            FileReader reader =
                    new FileReader(csvFilePath);

            CsvToBean<CSVStateCode> csvToBean =
                    new CsvToBeanBuilder<CSVStateCode>(reader)
                            .withType(CSVStateCode.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            Iterator<CSVStateCode> iterator =
                    csvToBean.iterator();

            int count = 0;

            while (iterator.hasNext()) {

                iterator.next();
                count++;
            }

            return count;

        } catch (java.io.FileNotFoundException e) {

            throw new CensusAnalyserException(
                    "File Not Found",
                    CensusAnalyserException.ExceptionType
                            .NO_SUCH_FILE);

        } catch (RuntimeException e) {

            throw new CensusAnalyserException(
                    "Incorrect Delimiter/Header",
                    CensusAnalyserException.ExceptionType
                            .INCORRECT_DELIMITER);

        } catch (Exception e) {

            throw new CensusAnalyserException(
                    "Error Loading State Code Data",
                    CensusAnalyserException.ExceptionType
                            .INCORRECT_HEADER);
        }
    }

    // Convert Object to JSON
    public String convertToJSON(Object data) {

        Gson gson = new Gson();

        return gson.toJson(data);
    }

    // Sort Example
    public void sortStates(List<CSVStateCensus> list) {

        list.sort(Comparator.comparing(
                CSVStateCensus::getState));

        System.out.println("States Sorted Successfully");
    }
}
