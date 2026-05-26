package com.day_28.io_stream;

public class Person {

    private String name;
    private String phone;
    private String email;
    private String city;
    private String state;

    public Person() {
    }

    public Person(String name, String phone, String email,
                  String city, String state) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {

        return "Name: " + name +
                ", Phone: " + phone +
                ", Email: " + email +
                ", City: " + city +
                ", State: " + state;
    }
}