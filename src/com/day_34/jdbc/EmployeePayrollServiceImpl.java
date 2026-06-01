package com.day_34.jdbc;



import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollServiceImpl {
    public List<EmployeePayroll> getEmployeePayrolls() throws EmployeePayrollException {
        List<EmployeePayroll> employeePayrolls = new ArrayList<>();

        String sql = "select id, name, salary, start_date from payroll_service.employee_payroll";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();

                employeePayrolls.add(new EmployeePayroll(id, name, salary, startDate));
            }
        } catch (Exception e) {
            throw new EmployeePayrollException("Unable to get employee payroll data");
        }
        return employeePayrolls;
    }

    public void updateEmployeeSalary(String employeeName, double salary) throws EmployeePayrollException {
        String sql = "update payroll_service.employee_payroll set salary = ? where name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, employeeName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeePayroll> getEmployeePayrollFromStartDate(LocalDate startDate) throws EmployeePayrollException {
        List<EmployeePayroll> employeePayrolls = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        String sql = "select id, name, salary, start_date from payroll_service.employee_payroll where start_date Between ? and ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, startDate.toString());
            preparedStatement.setString(2, currentDate.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                LocalDate startDate1 = resultSet.getDate("start_date").toLocalDate();

                employeePayrolls.add(new EmployeePayroll(id, name, salary, startDate1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return employeePayrolls;
    }

    public List<EmployeePayrollStats> getEmployeePayrollStats() throws EmployeePayrollException {
        List<EmployeePayrollStats> employeePayrollStats = new ArrayList<>();
        String sql = "select gender, SUM(salary) as total_salary, AVG(salary) as avg_salary, MIN(salary) as min_salary, MAX(salary) as max_salary, count(id) as emp_count from payroll_service.employee_payroll group by gender";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employeePayrollStats.add(new EmployeePayrollStats(
                        resultSet.getString("gender"),
                        resultSet.getDouble("total_salary"),
                        resultSet.getDouble("avg_salary"),
                        resultSet.getDouble("min_salary"),
                        resultSet.getDouble("max_salary"),
                        resultSet.getInt("emp_count")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeePayrollStats;
    }
}