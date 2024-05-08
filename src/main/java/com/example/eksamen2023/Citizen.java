package com.example.eksamen2023;

import java.time.LocalDate;

public class Citizen {
    private Long id;
    private String firstname;
    private String surname;
    private LocalDate DoB;
    private String SSN;
    private String phoneNumber;
    private String email;
    private String city;
    private String street;

    public Citizen(Long id, String firstname, String surname, LocalDate dateOfBirth, String socialSecurityNumber, String phoneNumber, String email, String city, String street) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.DoB = dateOfBirth;
        this.SSN = socialSecurityNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.street = street;
    }
    public Citizen(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate DoB) {
        this.DoB = DoB;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    @Override
    public String toString() {
        String ut;
        ut = "Citizen[firstname: "+getFirstname()+", surname: "+getSurname()+", dob: "+getDoB()+
                ", ssn: "+getSSN()+", phone: "+getPhoneNumber()+", email: "+getEmail()+
                ", city: "+getCity()+", street: "+getStreet()+"]";
        return ut;
    }
}
