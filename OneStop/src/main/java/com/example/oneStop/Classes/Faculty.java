package com.example.oneStop.Classes;

public class Faculty extends Users {

String department;
String position;
    public Faculty(int id, String name,String email, String address, String contactNo, String department, String position) {
        super(id, name, email, address, contactNo);
        this.department = department;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
