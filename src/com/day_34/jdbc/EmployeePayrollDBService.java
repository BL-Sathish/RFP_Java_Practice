package com.day_34.jdbc;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    private static EmployeePayrollDBService dbService;
    private PreparedStatement payrollByNameStmt;

    private EmployeePayrollDBService() {

    }

    public static EmployeePayrollDBService getInstance() {
        if (dbService == null) {
            dbService = new EmployeePayrollDBService();
        }
        return dbService;
    }
    public List<EmployeePayroll> readData() throws EmployeePayrollException {
        String sql = "SELECT id, name, salary, start_date FROM payroll_service.employee_payroll";
        List<EmployeePayroll> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new EmployeePayroll(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getDate("start_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to read payroll data");
        }
        return list;
    }

    private PreparedStatement getPayrollByNameStatement(Connection connection)
            throws SQLException {

        if (this.payrollByNameStmt == null) {
            String sql =
                    "SELECT id, name, salary, start_date FROM payroll_service.employee_payroll WHERE name = ?";
            this.payrollByNameStmt = connection.prepareStatement(sql);
        }
        return this.payrollByNameStmt;
    }

    public List<EmployeePayroll> getEmployeePayrollByName(String name)
            throws EmployeePayrollException {

        List<EmployeePayroll> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement stmt = getPayrollByNameStatement(connection);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new EmployeePayroll(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getDate("start_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to retrieve payroll by name");
        }
        return list;
    }
}
