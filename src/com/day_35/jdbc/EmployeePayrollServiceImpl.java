package com.day_35.jdbc;



public class EmployeePayrollServiceImpl {

    public EmployeePayroll addEmployeePayroll(String name, double salary, String gender, boolean is_active) throws EmployeePayrollException {
        EmployeePayroll employeePayroll = EmployeePayrollDBService.addEmployeeToDB(name, salary, gender, is_active);
        return employeePayroll;
    }

    public EmployeePayroll addEmployeePayrollDetails(String name, double salary, String gender, boolean is_active) throws EmployeePayrollException {
        EmployeePayroll employeePayroll = EmployeePayrollDBService.addEmployeeWithPayrollDetails(name, salary, gender, is_active);
        return employeePayroll;
    }

    public boolean removeEmployee(int employeeId) throws EmployeePayrollException {
        boolean isRemoved = EmployeePayrollDBService.removeEmployeeFromDB(employeeId);
        if (isRemoved) {
            return true;
        } else {
            return false;
        }
    }
}