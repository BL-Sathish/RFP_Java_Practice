package com.day_34.jdbc;



import java.time.LocalDate;
import java.util.List;

public class JDBCMain {
    public static void main(String[] args) throws EmployeePayrollException {
        EmployeePayrollServiceImpl employeePayrollService = new EmployeePayrollServiceImpl();

        List<EmployeePayroll> employeePayrolls = employeePayrollService.getEmployeePayrolls();
        employeePayrolls.forEach(System.out::println);

        employeePayrollService.updateEmployeeSalary("Emily Davis", 3000000.00);

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        List<EmployeePayroll> employees = employeePayrollService.getEmployeePayrollFromStartDate(startDate);
        employees.forEach(System.out::println);

        List<EmployeePayrollStats> employeePayrollStats = employeePayrollService.getEmployeePayrollStats();
        employeePayrollStats.forEach(System.out::println);
    }
}