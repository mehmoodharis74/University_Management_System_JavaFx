package com.example.oneStop.Classes;

public class Transcript_SemesterRecord {
    String credits;
    String points;
    String grades;
    String courses;

    public Transcript_SemesterRecord(String courses, String credits, String points, String grades) {
        this.courses = courses;
        this.credits = credits;
        this.points = points;
        this.grades = grades;

    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }








}
