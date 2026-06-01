package com.day_34.jdbc;



import java.time.LocalDate;

public class EmployeePayroll {
    private int id;
    private String name;
    private double salary;
    private LocalDate date;

    public EmployeePayroll(int id, String name, double salary, LocalDate date) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmployeePayroll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }
}
