package com.example.oneStop.Classes;

public class Degree {
    int studentId;

    int adminId;
    String degreeName;
    String issueDate;
    String degreeLocation,studentName;

    public Degree( int studentId,int adminId, String degreeName, String issueDate, String degreeLocation, String studentName) {
        this.studentId = studentId;
        this.adminId = adminId;
        this.degreeName = degreeName;
        this.issueDate = issueDate;
        this.degreeLocation = degreeLocation;
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public String getDegreeLocation() {
        return degreeLocation;
    }

}
