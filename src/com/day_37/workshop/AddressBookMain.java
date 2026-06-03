package com.day_37.workshop;




import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person");
            System.out.println("3. Delete Person");
            System.out.println("4. Display All");
            System.out.println("5. Sort Persons by Name");
            System.out.println("6. Sort by State");
            System.out.println("7. Sort by Zip");
            System.out.println("8. View Persons by City");
            System.out.println("9. View Persons by State");
            System.out.println("10. Search Person by City");
            System.out.println("11. Search Person by State");
            System.out.println("12. Write to JSON");
            System.out.println("13. Read from JSON");
            System.out.println("14. Write to CSV");
            System.out.println("15. Read from CSV");
            System.out.println("16. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter First Name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = sc.nextLine();

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter City: ");
                    String city = sc.nextLine();

                    System.out.print("Enter State: ");
                    String state = sc.nextLine();

                    System.out.print("Enter Zip: ");
                    String zip = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    Person person = new Person(firstName, lastName, address, city, state, zip, email, phone);
                    addressBook.addPerson(person);
                    break;

                case 2:
                    System.out.print("Enter First Name to Edit: ");
                    String editFirstName = sc.nextLine();
                    addressBook.editPerson(editFirstName);
                    break;

                case 3:
                    System.out.print("Enter First Name to Delete: ");
                    String deleteFirstName = sc.nextLine();
                    addressBook.deletePerson(deleteFirstName);
                    break;

                case 4:
                    addressBook.displayAddresses();
                    break;

                case 5:
                    addressBook.sortByName();
                    break;

                case 6:
                    addressBook.sortByCity();
                    break;

                case 7:
                    addressBook.sortByState();
                    break;

                case 8:
                    System.out.print("Enter City: ");
                    addressBook.viewPersonsByCity(sc.nextLine());
                    break;

                case 9:
                    System.out.print("Enter State: ");
                    addressBook.viewPersonsByState(sc.nextLine());
                    break;

                case 10:
                    System.out.print("Enter City to search: ");
                    addressBook.searchByCity(sc.nextLine());
                    break;

                case 11:
                    System.out.print("Enter State to search: ");
                    addressBook.searchByState(sc.nextLine());
                    break;

                case 12:
                    addressBook.writeToJson();
                    break;

                case 13:
                    addressBook.readFromJson();
                    break;

                case 14:
                    addressBook.writeToCsv();
                    break;

                case 15:
                    addressBook.readFromCsv();
                    break;

                case 16:
                    System.out.println("Exit");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
