package com.day_35.jdbc;



public class EmployeePayrollMain {
    public static void main(String[] args) throws EmployeePayrollException {
        EmployeePayrollServiceImpl employeePayrollService = new EmployeePayrollServiceImpl();
        boolean status = employeePayrollService.removeEmployee(3);
        System.out.println(status);
    }
}
