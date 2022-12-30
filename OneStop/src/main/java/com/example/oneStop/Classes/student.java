package com.example.oneStop.Classes;

public class student extends Users {
    int id;
    String name;

    public student(int id, String name,String email, String address, String contactNo) {
        super(id, name, email, address, contactNo);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
