package com.day_28.io_stream;

import java.util.HashMap;
import java.util.Scanner;

public class AddressBookSystem {

    static Scanner sc = new Scanner(System.in);

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
            System.out.println("6. Write TXT File");
            System.out.println("7. Read TXT File");
            System.out.println("8. Write CSV File");
            System.out.println("9. Read CSV File");
            System.out.println("10. Write JSON File");
            System.out.println("11. Read JSON File");
            System.out.println("12. Exit");

            System.out.print("Enter Choice: ");

            int choice =
                    Integer.parseInt(sc.nextLine());

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
                    writeTXT();
                    break;

                case 7:
                    readTXT();
                    break;

                case 8:
                    writeCSV();
                    break;

                case 9:
                    readCSV();
                    break;

                case 10:
                    writeJSON();
                    break;

                case 11:
                    readJSON();
                    break;

                case 12:
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void createAddressBook() {

        System.out.print("Enter Address Book Name: ");

        String name = sc.nextLine();

        if (addressBooks.containsKey(name)) {

            System.out.println("Already Exists");

        } else {

            addressBooks.put(name, new AddressBook());

            System.out.println("Address Book Created");
        }
    }

    static AddressBook getBook() {

        System.out.print("Enter Address Book Name: ");

        String name = sc.nextLine();

        AddressBook book = addressBooks.get(name);

        if (book == null) {

            System.out.println("Address Book Not Found");
        }

        return book;
    }

    static void addPerson() {

        AddressBook book = getBook();

        if (book == null) return;

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

    static void viewPersons() {

        AddressBook book = getBook();

        if (book != null) {

            book.viewPersons();
        }
    }

    static void searchByCity() {

        AddressBook book = getBook();

        if (book != null) {

            System.out.print("Enter City: ");

            String city = sc.nextLine();

            book.searchByCity(city);
        }
    }

    static void searchByState() {

        AddressBook book = getBook();

        if (book != null) {

            System.out.print("Enter State: ");

            String state = sc.nextLine();

            book.searchByState(state);
        }
    }

    static void writeTXT() {

        AddressBook book = getBook();

        if (book != null) {

            book.writeTextFile("contacts.txt");
        }
    }

    static void readTXT() {

        AddressBook book = getBook();

        if (book != null) {

            book.readTextFile("contacts.txt");
        }
    }

    static void writeCSV() {

        AddressBook book = getBook();

        if (book != null) {

            book.writeCSV("contacts.csv");
        }
    }

    static void readCSV() {

        AddressBook book = getBook();

        if (book != null) {

            book.readCSV("contacts.csv");
        }
    }

    static void writeJSON() {

        AddressBook book = getBook();

        if (book != null) {

            book.writeJSON("contacts.json");
        }
    }

    static void readJSON() {

        AddressBook book = getBook();

        if (book != null) {

            book.readJSON("contacts.json");
        }
    }
}
