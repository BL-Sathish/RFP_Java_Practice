package com.day_27.io_stream;



import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EmployeePayrollService {

 private static final String DIRECTORY = "payroll_data";
 private static final String FILE_PATH = DIRECTORY + "/employee_payroll.txt";

 // Check File Exists
 public void checkFileExists() {
     Path path = Paths.get(FILE_PATH);

     if (Files.exists(path)) {
         System.out.println("File Exists");
     } else {
         System.out.println("File Not Exists");
     }
 }

 // Delete File
 public void deleteFile() throws IOException {
     Path path = Paths.get(FILE_PATH);

     Files.deleteIfExists(path);

     if (!Files.exists(path)) {
         System.out.println("File Deleted Successfully");
     }
 }

 // Create Directory
 public void createDirectory() throws IOException {
     Path path = Paths.get(DIRECTORY);

     if (!Files.exists(path)) {
         Files.createDirectories(path);
         System.out.println("Directory Created");
     } else {
         System.out.println("Directory Already Exists");
     }
 }

 // Create Empty File
 public void createEmptyFile() throws IOException {
     Path path = Paths.get(FILE_PATH);

     if (!Files.exists(path)) {
         Files.createFile(path);
         System.out.println("Empty File Created");
     } else {
         System.out.println("File Already Exists");
     }
 }

 // List Files and Directories
 public void listFilesAndDirectories() throws IOException {
     Path path = Paths.get(".");

     System.out.println("\nFiles and Directories:");

     try (Stream<Path> paths = Files.list(path)) {
         paths.forEach(System.out::println);
     }
 }

 // List Files with Extension
 public void listTxtFiles() throws IOException {
     Path path = Paths.get(".");

     System.out.println("\nTXT Files:");

     try (Stream<Path> paths = Files.walk(path)) {
         paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().endsWith(".txt"))
                 .forEach(System.out::println);
     }
 }

 // Write Employee Payroll to File
 public void writeEmployeePayroll(List<EmployeePayroll> employees) throws IOException {

     List<String> employeeData = new ArrayList<>();

     for (EmployeePayroll emp : employees) {
         employeeData.add(emp.toString());
     }

     Files.write(Paths.get(FILE_PATH),
             employeeData,
             StandardOpenOption.CREATE,
             StandardOpenOption.APPEND);

     System.out.println("Employee Payroll Written to File");
 }

 // Print Employee Payrolls
 public void printEmployeePayrolls() throws IOException {

     System.out.println("\nEmployee Payroll Data:");

     Files.lines(Paths.get(FILE_PATH))
             .forEach(System.out::println);
 }

 // Count Number of Entries
 public long countEntries() throws IOException {

     long count = Files.lines(Paths.get(FILE_PATH)).count();

     System.out.println("\nNumber of Entries : " + count);

     return count;
 }

 // Read Employee Payroll File
 public void readEmployeePayrollFile() throws IOException {

     System.out.println("\nReading Employee Payroll File:");

     Files.lines(Paths.get(FILE_PATH))
             .forEach(line -> {
                 String[] data = line.split(",");

                 int id = Integer.parseInt(data[0]);
                 String name = data[1];
                 double salary = Double.parseDouble(data[2]);

                 System.out.println("ID : " + id);
                 System.out.println("Name : " + name);
                 System.out.println("Salary : " + salary);
                 System.out.println("----------------------");
             });
 }

 // Watch Service
 public void watchDirectory() throws IOException, InterruptedException {

     WatchService watchService = FileSystems.getDefault().newWatchService();

     Path path = Paths.get(DIRECTORY);

     path.register(watchService,
             StandardWatchEventKinds.ENTRY_CREATE,
             StandardWatchEventKinds.ENTRY_DELETE,
             StandardWatchEventKinds.ENTRY_MODIFY);

     System.out.println("\nWatching Directory : " + DIRECTORY);

     while (true) {

         WatchKey key = watchService.take();

         for (WatchEvent<?> event : key.pollEvents()) {

             WatchEvent.Kind<?> kind = event.kind();

             System.out.println("Event : " + kind.name());
             System.out.println("File : " + event.context());
         }

         boolean valid = key.reset();

         if (!valid) {
             break;
         }
     }
 }
}
