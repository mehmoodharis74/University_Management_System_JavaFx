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

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDegreeLocation() {
        return degreeLocation;
    }

    public void setDegreeLocation(String degreeLocation) {
        this.degreeLocation = degreeLocation;
    }
}
