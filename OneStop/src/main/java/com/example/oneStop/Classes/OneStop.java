package com.example.oneStop.Classes;

import com.example.oneStop.Constants.Constants;
import com.example.oneStop.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class OneStop {
    @FXML
    Button login_page_loginBtn,logoutButton,
            SubmitDegreeBtn;

@FXML
Label backArrow;


    //student all button navigation's
    private static database db = null;
    public static String[] path =new String[10];
    public static Integer pathIndex=0;


    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void applyForDegreeBtn_onAction(){

        pageLoader("apply_degree_main_page.fxml");
    }

    public void applyForComplainBtn_onAction(){


        pageLoader("complainRegistration_main_page.fxml");
    }
    public void trackActivityBtn_onAction(){
      pageLoader("trackActivity_main_page.fxml");
    }
    public void viewDegreeBtn_onAction() {
       pageLoader("view_degree_main_page.fxml");

    }


    public void applyForTranscriptBtn_onAction(){


        //insert into request table
        String query = "insert into transcript (studentId,granted,requested) values (?,?,?)";
        try {
            PreparedStatement ps = db.connection.prepareStatement(query);
            ps.setInt(1, Constants.CURRENT_USER_ID);
            ps.setString(2, "no");
            ps.setString(3, "yes");
            ps.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transcript_SemesterRecord Request");
            alert.setHeaderText("Transcript_SemesterRecord Request");
            alert.setContentText("Your Transcript_SemesterRecord Request has been submitted successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Transcript_SemesterRecord Request");
            alert.setHeaderText("Transcript_SemesterRecord Request");
            alert.setContentText("Your Transcript_SemesterRecord Request has not been submitted successfully\n" +
                    "Got error in database");
            alert.showAndWait();
        }

    }
    public void viewTranscriptBtn_onAction()  {
        pageLoader("view_transcript_main.fxml");

    }
    public void generateFeeInstallment_onAction(){
        pageLoader("generateFeeInstallment.fxml");
    }
    //admin all button navigation's
    public void manageRequests_onAction(){
        pageLoader("manage_requests_page.fxml");
    }
    public void issueDegree_onAction(){
        Constants.ADMIN_PRESSED_BUTTON = "issueDegree";
        pageLoader("manage_requests_page.fxml");
    }
    public void issueTranscript_onAction(){
        Constants.ADMIN_PRESSED_BUTTON = "issueTranscript";
        pageLoader("manage_requests_page.fxml");
    }

    //manage Requests Button
    protected void generateInstallmentRequest() {

    }

    protected boolean issueDegree(String token) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currDate = formatter.format(date);
String query = "UPDATE request SET rStatus = 'Completed', track='Completed', degree_issued= 'yes' , e_time= ? WHERE token = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, currDate);
            ps.setInt(2, Integer.parseInt(token));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saveDegree(token);
    }

    public void issueTranscriptButton_onAction(ActionEvent event){

      //  issueTranscript();
    }
//apply for degree request page
public void submit_degree_request(String name, String fname, String address,String email, String degree,String campus, String cgpa){


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currDate = formatter.format(date);
    String query = "insert into request(studentid,track,s_time,rstatus,admin_starttime,student_name,father_name,saddress,degree,email,cgpa,campus,degree_issued)" +
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(query);
        ps.setInt(1,Constants.CURRENT_USER_ID);
        ps.setString(2,"admin");
        ps.setString(3,currDate);
        ps.setString(4,"pending");
        ps.setString(5,currDate);
        ps.setString(6,name.toUpperCase());
        ps.setString(7,fname.toUpperCase());
        ps.setString(8,address);
        ps.setString(9,degree.toUpperCase());
        ps.setString(10,email);
        ps.setString(11,cgpa);
        ps.setString(12,campus.toUpperCase());
        ps.setString(13,"NO");
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    //logout button navigation
    @FXML
    protected void logOut_onAction() {
        //navigate to login page
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setMaximized(true);
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource("login_page.fxml")))));
            Constants.CURRENT_USER_NAME = "";
            Constants.CURRENT_USER_ID = 0;
            Constants.CURRENT_USER_TYPE = "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
pathIndex=0;
        Constants.clearStrings();
    }

    protected void pageLoader(String page) {
        if (pathIndex!=0 && path[pathIndex-1].equals(page)) {
            pathIndex--;
        }
        path[pathIndex]=page;
        Stage stage;

        if(logoutButton !=null )
        stage = (Stage) logoutButton.getScene().getWindow();
        else {
            stage = (Stage) login_page_loginBtn.getScene().getWindow();
        }
//        if(!stage.isMaximized()) {
//            stage.setMaximized(true);
//        }
       /* GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = graphicsDevice.getDisplayMode().getWidth();
        int height = graphicsDevice.getDisplayMode().getHeight();*/

        try {
           /* stage.setHeight(height);
            stage.setWidth(width);*/
            stage.setMinWidth(900);
            stage.setMinHeight(640);
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(path[pathIndex])))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pathIndex++;
    }

    public void backArrow_onClick() {
        pathIndex--;
        pageLoader(path[pathIndex-1]);
    }

    public boolean verifyUserLogin(String id, String password, String user_type) {


        String query = null;
        boolean found = false;
        if (Objects.equals(user_type, "Student"))
            query = "SELECT id, sName, passwords FROM student WHERE id = " + id + " AND passwords = '" + password + "'";
        else if (Objects.equals(user_type, "FypCommittee"))
            query = "SELECT id, fyp_name , passwords FROM fyp WHERE id = " + id + " AND passwords = '" + password + "'";
        else if (Objects.equals(user_type, "Admin"))
            query = "SELECT id,  a_name, passwords FROM admin WHERE id = " + id + " AND passwords = '" + password + "'";
        else if (Objects.equals(user_type, "Director"))
            query = "SELECT id ,d_name, passwords FROM director WHERE id = " + id + " AND passwords = '" + password + "'";
        else if (Objects.equals(user_type, "FinanceCommittee"))
            query = "SELECT id ,f_name, passwords FROM finance WHERE id = " + id + " AND passwords = '" + password + "'";

        ResultSet reader = db.executeQuery(query);
        while (true) {
            try {
                if (!reader.next()) break;
                else if (reader.getInt("id") == Integer.parseInt(id) &&
                        reader.getString("passwords").equals(password)) {
                    found = true;
                    Constants.CURRENT_USER_ID = Integer.parseInt(id);
                    if (Objects.equals(user_type, "Student")) {
                        Constants.CURRENT_USER_NAME = reader.getString("sName");
                        Constants.CURRENT_USER_TYPE = "Student";
                    }
                    else if (Objects.equals(user_type, "FypCommittee")) {
                        Constants.CURRENT_USER_NAME = reader.getString("fyp_name");
                        Constants.CURRENT_USER_TYPE = "Fyp";
                    }
                    else if (Objects.equals(user_type, "Admin")) {
                        Constants.CURRENT_USER_NAME = reader.getString("a_Name");
                        Constants.CURRENT_USER_TYPE = "Admin";
                    }
                    else if (Objects.equals(user_type, "Director")) {
                        Constants.CURRENT_USER_NAME = reader.getString("d_Name");
                        Constants.CURRENT_USER_TYPE = "Director";
                    }
                    else if (Objects.equals(user_type, "FinanceCommittee")) {
                        Constants.CURRENT_USER_NAME = reader.getString("f_Name");
                        Constants.CURRENT_USER_TYPE = "Finance";
                    }
                   // if(navName != null)

                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return found;
    }
    public boolean degreeIssued(String tkn){
       return db.isDegreeIssued(tkn);
    }
    public boolean isTranscriptIssued(String tkn){
        return db.isTranscriptIssued(tkn);
    }
    public boolean issueTranscript(String tkn){
       return db.issueTranscript(tkn);
    }
    public String getStudentName(String sid){
        return db.getStudentNameBySID(sid);
    }
    public boolean getDegreeRequestStatus(String tkn){
        return db.getDegreeRequestStatus(tkn);
    }
    public boolean isDegreeSaved(String sid){
        return db.isDegreeSaved(sid);
    }

    protected Degree loadDegree() {
        String tkn = String.valueOf(Constants.CURRENT_USER_ID);
        String query = "select * from degree where studentId = " + tkn;
        ResultSet reader = db.executeQuery(query);
        Degree degree = null;
        try {
            while (reader.next()) {

                degree = new Degree(reader.getInt("studentId"), reader.getInt("adminId"), reader.getString("degree_name"),
                        reader.getString("issued_date"), reader.getString("locationName"), reader.getString("student_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return degree;
    }
    public boolean saveDegree(String token) {
        Degree degree = null;
        if (db.isDegreeIssued(token)) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currDate = formatter.format(date);
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            String degDate = formatter.format(date);
            String query = "select * from request where token = " + token;
            ResultSet reader = db.executeQuery(query);
            try {
                if (reader.next()) {
                    degree = new Degree(reader.getInt("studentId"),reader.getInt("adminId"),reader.getString("degree"),
                            degDate,reader.getString("campus"),reader.getString("student_name"));
                    String query1 = "insert into degree(degree_name,studentId,adminId,Issued_date,locationName, student_name)" +
                            "values(?,?,?,?,?,?)";
                    PreparedStatement ps = db.getConnection().prepareStatement(query1);
                    ps.setString(1,degree.getDegreeName());
                    ps.setInt(2, degree.getStudentId());
                    ps.setInt(3, degree.getAdminId());
                    ps.setString(4, currDate);
                    ps.setString(5, degree.getDegreeLocation());
                    ps.setString(6, degree.getStudentName());
                    ps.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }
    protected int getMaxSemester(String query){
        ResultSet reader = db.executeQuery(query);
            try {
                if (!reader.next()) return -1;
                else  {
                    return reader.getInt("max");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
}
