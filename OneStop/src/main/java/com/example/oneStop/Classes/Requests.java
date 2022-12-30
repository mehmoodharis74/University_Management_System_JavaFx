package com.example.oneStop.Classes;

public class Requests {
    int tokenId, studentId;
    String requestName, requestDegree, degreeIssued;

    public Requests(int tokenId, int studentId, String requestName, String requestDegree, String degreeIssued) {
        this.tokenId = tokenId;
        this.studentId = studentId;
        this.requestName = requestName;
        this.requestDegree = requestDegree;
        this.degreeIssued = degreeIssued;

    }

    public int getTokenId() {
        return tokenId;
    }
    public int getStudentId() {
        return studentId;
    }
    public String getRequestName() {
        return requestName;
    }
    public String getRequestDegree() {
        return requestDegree;
    }
    public String getDegreeIssued() {
        return degreeIssued;
    }




}
