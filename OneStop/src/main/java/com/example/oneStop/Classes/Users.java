package com.example.oneStop.Classes;

public class Users {


    int id;
    String name;
    String email;
    String address;
    String contactNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Users(int id, String name, String email, String address, String contactNo) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.contactNo = contactNo;
        }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
