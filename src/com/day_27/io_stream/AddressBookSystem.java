package com.day_27.io_stream;

import java.io.*;
import java.util.*;

class Person {

    String name;
    String phone;
    String email;
    String city;
    String state;

    public Person(String name, String phone, String email,
                  String city, String state) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {

        return "Name: " + name +
                ", Phone: " + phone +
                ", Email: " + email +
                ", City: " + city +
                ", State: " + state;
    }
}

class AddressBook {

    ArrayList<Person> persons = new ArrayList<>();

    // Add Person
    public void addPerson(Person person) {

        // Duplicate Check
        for (Person p : persons) {

            if (p.name.equalsIgnoreCase(person.name)) {

                System.out.println("Duplicate Entry! Person already exists.");
                return;
            }
        }

        persons.add(person);
        System.out.println("Person Added Successfully!");
    }

    // View All Persons
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
    public void viewByCity(String city) {

        boolean found = false;

        for (Person p : persons) {

            if (p.city.equalsIgnoreCase(city)) {

                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Person Found in City");
        }
    }

    // Search By State
    public void viewByState(String state) {

        boolean found = false;

        for (Person p : persons) {

            if (p.state.equalsIgnoreCase(state)) {

                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Person Found in State");
        }
    }

    // Write to File
    public void writeToFile(String fileName) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName));

            for (Person p : persons) {

                writer.write(p.toString());
                writer.newLine();
            }

            writer.close();

            System.out.println("Data Written to File Successfully");

        } catch (IOException e) {

            System.out.println("File Write Error");
        }
    }

    // Read From File
    public void readFromFile(String fileName) {

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("File Read Error");
        }
    }
}

public class AddressBookSystem {

    static Scanner sc = new Scanner(System.in);

    // Dictionary of Address Books
    static HashMap<String, AddressBook> addressBooks =
            new HashMap<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== ADDRESS BOOK SYSTEM =====");

            System.out.println("1. Create Address Book");
            System.out.println("2. Add Person");
            System.out.println("3. View Persons");
            System.out.println("4. Search By City");
            System.out.println("5. Search By State");
            System.out.println("6. Write To File");
            System.out.println("7. Read From File");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    createAddressBook();
                    break;

                case 2:
                    addPerson();
                    break;

                case 3:
                    viewPersons();
                    break;

                case 4:
                    searchByCity();
                    break;

                case 5:
                    searchByState();
                    break;

                case 6:
                    writeFile();
                    break;

                case 7:
                    readFile();
                    break;

                case 8:
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    // Create Address Book
    static void createAddressBook() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        if (addressBooks.containsKey(bookName)) {

            System.out.println("Address Book Already Exists");

        } else {

            addressBooks.put(bookName, new AddressBook());

            System.out.println("Address Book Created");
        }
    }

    // Add Person
    static void addPerson() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {

            System.out.println("Address Book Not Found");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        System.out.print("Enter State: ");
        String state = sc.nextLine();

        Person person =
                new Person(name, phone, email, city, state);

        book.addPerson(person);
    }

    // View Persons
    static void viewPersons() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        AddressBook book = addressBooks.get(bookName);

        if (book != null) {

            book.viewPersons();

        } else {

            System.out.println("Address Book Not Found");
        }
    }

    // Search By City
    static void searchByCity() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        AddressBook book = addressBooks.get(bookName);

        if (book != null) {

            System.out.print("Enter City: ");

            String city = sc.nextLine();

            book.viewByCity(city);

        } else {

            System.out.println("Address Book Not Found");
        }
    }

    // Search By State
    static void searchByState() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        AddressBook book = addressBooks.get(bookName);

        if (book != null) {

            System.out.print("Enter State: ");

            String state = sc.nextLine();

            book.viewByState(state);

        } else {

            System.out.println("Address Book Not Found");
        }
    }

    // Write File
    static void writeFile() {

        System.out.print("Enter Address Book Name: ");

        String bookName = sc.nextLine();

        AddressBook book = addressBooks.get(bookName);

        if (book != null) {

            book.writeToFile(bookName + ".txt");

        } else {

            System.out.println("Address Book Not Found");
        }
    }

    // Read File
    static void readFile() {

        System.out.print("Enter File Name: ");

        String fileName = sc.nextLine();

        AddressBook temp = new AddressBook();

        temp.readFromFile(fileName);
    }
}
