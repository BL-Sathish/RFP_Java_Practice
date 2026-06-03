package com.day_37.workshop;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    private static final String FILE_PATH = "D:\\learning\\AddressBook.txt";
    private static final String CSV_FILE = "D:\\learning\\AddressBook.csv";
    final Gson gson = new Gson();
    List<Person> addresses = new ArrayList<>();
    Map<String, List<Person>> cityMap = new HashMap<>();
    Map<String, List<Person>> stateMap = new HashMap<>();

    // UC1 & UC5
    public void addPerson(Person person) {
        boolean isDuplicate = addresses.stream().anyMatch(existingPerson -> existingPerson.equals(person));
        if (isDuplicate) {
            System.out.println("This person already exists");
            return;
        }
        addresses.add(person);

        if (cityMap.containsKey(person.getCity())) {
            cityMap.get(person.getCity()).add(person);
        } else {
            List<Person> cityList = new ArrayList<>();
            cityList.add(person);
            cityMap.put(person.getCity(), cityList);
        }

        if (stateMap.containsKey(person.getState())) {
            stateMap.get(person.getState()).add(person);
        } else {
            List<Person> stateList = new ArrayList<>();
            stateList.add(person);
            stateMap.put(person.getState(), stateList);
        }

        System.out.println("Person added successfully!");
    }

    public void displayAddresses() {
        if (addresses.isEmpty()) {
            System.out.println("Nothing to Display!");
            return;
        }

        System.out.println("----- Address Book -----");
        for (Person person : addresses) {
            System.out.println(person);
            System.out.println("----------------------");
        }
    }

    public void editPerson(String firstName) {
        Scanner sc = new Scanner(System.in);
        boolean found = false;

        for (Person person : addresses) {
            if (person.getFirstName().equals(firstName)) {
                found = true;

                System.out.print("Enter New Address: ");
                person.setAddress(sc.nextLine());

                System.out.print("Enter New City: ");
                person.setCity(sc.nextLine());

                System.out.print("Enter New State: ");
                person.setState(sc.nextLine());

                System.out.print("Enter New Zip: ");
                person.setZip(sc.nextLine());

                System.out.print("Enter New Email: ");
                person.setEmail(sc.nextLine());

                System.out.print("Enter New Phone Number: ");
                person.setPhoneNumber(sc.nextLine());

                System.out.println("Person details updated successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Person not found!");
        }
    }

    public void deletePerson(String deleteFirstName) {
        boolean found = false;
        for (Person person : addresses) {
            if (person.getFirstName().equals(deleteFirstName)) {
                found = true;
                addresses.remove(person);
                System.out.println("Person deleted successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Person not found!");
        }
    }

    public void sortByName() {

        List<Person> sortedList = addresses.stream()
                .sorted(Comparator.comparing(Person::getFirstName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        if (sortedList.isEmpty()) {
            System.out.println("Nothing to Display!");
            return;
        }
        System.out.println("----- Address Book (Sorted by Name) -----");
        sortedList.forEach(System.out::println);
        System.out.println("---------------------");
    }

    public void sortByCity() {
        List<Person> personListSortedByCity = addresses.stream()
                .sorted(Comparator.comparing(Person::getCity, String.CASE_INSENSITIVE_ORDER))
                .toList();
        personListSortedByCity.forEach(System.out::println);
    }

    public void sortByState() {
        addresses.stream()
                .sorted(Comparator.comparing(Person::getState, String.CASE_INSENSITIVE_ORDER))
                .toList().forEach(System.out::println);
    }

    public void viewPersonsByCity(String city) {
        if (!cityMap.containsKey(city)) {
            System.out.println("No persons found in city: " + city);
            return;
        }
        System.out.println("Persons in City: " + city);
        for (Person p : cityMap.get(city)) {
            System.out.println(p);
            System.out.println("----------------------------------------");
        }
    }

    public void viewPersonsByState(String state) {
        if (!stateMap.containsKey(state)) {
            System.out.println("No persons found in state: " + state);
            return;
        }
        System.out.println("Persons in State: " + state);
        for (Person p : stateMap.get(state)) {
            System.out.println(p);
            System.out.println("----------------------------------------");
        }
    }

    public void searchByCity(String city) {
        List<Person> result = addresses.stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        printSearchResult("City", city, result);
    }

    public void searchByState(String state) {
        List<Person> result = addresses.stream()
                .filter(p -> p.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
        printSearchResult("State", state, result);
    }

    public void printSearchResult(String type, String value, List<Person> list) {
        if (list.isEmpty()) {
            System.out.println("No persons found in " + type + ": " + value);
            return;
        }
        System.out.println("Persons found in " + type + ": " + value);
        list.forEach(p -> {
            System.out.println(p);
            System.out.println("----------------------------------------");
        });
    }

    public void writeToJson() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(addresses, writer);
            System.out.println("Address Book written to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJson() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Person>>() {}.getType();
            addresses = gson.fromJson(reader, listType);

            if (addresses == null) {
                addresses = new ArrayList<>();
            }
            System.out.println("Address Book loaded from JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCsv() {
        try (Writer writer = new FileWriter(CSV_FILE)) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer).build();
            beanToCsv.write(addresses);
            System.out.println("Address Book written to CSV file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromCsv() {
        try (Reader reader = new FileReader(CSV_FILE)) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            addresses = csvToBean.parse();
            System.out.println("Address Book loaded from CSV file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
