package com.day_28.io_stream;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddressBook {

    private ArrayList<Person> persons = new ArrayList<>();

    // Add Person
    public void addPerson(Person person) {

        for (Person p : persons) {

            if (p.getName().equalsIgnoreCase(person.getName())) {

                System.out.println("Duplicate Entry Found!");
                return;
            }
        }

        persons.add(person);

        System.out.println("Person Added Successfully");
    }

    // View Persons
    public void viewPersons() {

        if (persons.isEmpty()) {

            System.out.println("No Contacts Available");
            return;
        }

        for (Person p : persons) {

            System.out.println(p);
        }
    }

    // Search By City
    public void searchByCity(String city) {

        boolean found = false;

        for (Person p : persons) {

            if (p.getCity().equalsIgnoreCase(city)) {

                System.out.println(p);
                found = true;
            }
        }

        if (!found) {

            System.out.println("No Person Found");
        }
    }

    // Search By State
    public void searchByState(String state) {

        boolean found = false;

        for (Person p : persons) {

            if (p.getState().equalsIgnoreCase(state)) {

                System.out.println(p);
                found = true;
            }
        }

        if (!found) {

            System.out.println("No Person Found");
        }
    }

    // Write TXT File
    public void writeTextFile(String fileName) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName));

            for (Person p : persons) {

                writer.write(p.toString());
                writer.newLine();
            }

            writer.close();

            System.out.println("Text File Written Successfully");

        } catch (IOException e) {

            System.out.println("Error Writing File");
        }
    }

    // Read TXT File
    public void readTextFile(String fileName) {

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error Reading File");
        }
    }

    // Write CSV
    public void writeCSV(String fileName) {

        try {

            CSVWriter writer =
                    new CSVWriter(new FileWriter(fileName));

            String[] header =
                    {"Name", "Phone", "Email", "City", "State"};

            writer.writeNext(header);

            for (Person p : persons) {

                String[] data = {
                        p.getName(),
                        p.getPhone(),
                        p.getEmail(),
                        p.getCity(),
                        p.getState()
                };

                writer.writeNext(data);
            }

            writer.close();

            System.out.println("CSV File Written Successfully");

        } catch (Exception e) {

            System.out.println("CSV Write Error");
        }
    }

    // Read CSV
    public void readCSV(String fileName) {

        try {

            CSVReader reader =
                    new CSVReader(new FileReader(fileName));

            List<String[]> records = reader.readAll();

            for (String[] row : records) {

                for (String data : row) {

                    System.out.print(data + " ");
                }

                System.out.println();
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("CSV Read Error");
        }
    }

    // Write JSON
    public void writeJSON(String fileName) {

        try {

            Gson gson = new Gson();

            FileWriter writer = new FileWriter(fileName);

            gson.toJson(persons, writer);

            writer.close();

            System.out.println("JSON File Written Successfully");

        } catch (Exception e) {

            System.out.println("JSON Write Error");
        }
    }

    // Read JSON
    public void readJSON(String fileName) {

        try {

            Gson gson = new Gson();

            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));

            Type type =
                    new TypeToken<ArrayList<Person>>(){}.getType();

            ArrayList<Person> list =
                    gson.fromJson(reader, type);

            for (Person p : list) {

                System.out.println(p);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("JSON Read Error");
        }
    }
}
