package com.day_27.io_stream;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Employee_Payroll_Main {

 public static void main(String[] args) {

     EmployeePayrollService service = new EmployeePayrollService();

     try {

         // Create Directory
         service.createDirectory();

         // Create Empty File
         service.createEmptyFile();

         // Check File Exists
         service.checkFileExists();

         // List Files and Directories
         service.listFilesAndDirectories();

         // List TXT Files
         service.listTxtFiles();

         // Create Employee Objects
         List<EmployeePayroll> employees = new ArrayList<>();

         employees.add(new EmployeePayroll(1, "Sathish", 50000));
         employees.add(new EmployeePayroll(2, "Arun", 45000));
         employees.add(new EmployeePayroll(3, "Kavin", 60000));

         // Write Employee Payroll to File
         service.writeEmployeePayroll(employees);

         // Print Employee Payrolls
         service.printEmployeePayrolls();

         // Count Entries
         service.countEntries();

         // Read Employee Payroll File
         service.readEmployeePayrollFile();

         /*
         // Watch Service
         // Uncomment to watch directory continuously

         service.watchDirectory();
         */

         // Delete File
         // service.deleteFile();

     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
