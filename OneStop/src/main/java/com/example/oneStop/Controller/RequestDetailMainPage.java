package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Classes.database;
import com.example.oneStop.Constants.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RequestDetailMainPage extends OneStop {
    @FXML
    Label studentID_label, studentName_label, RStatus_label, RTrack_label, financeDes_label,financeCom_label,FYPDes_label,FYPCom_label,navName,admin_signature,hod_signature;
@FXML
Button admin_sendFypButton, admin_addSignatureButton, finance_sendToAdminButton, sendToFinanceButton, financeApproveButton, financeRejectButton, fypApproveButton, fypRejectButton, directorAddSignature;
@FXML
    HBox adminHBox, fypHBox, financeHBox, directorHBox;
    private static database db;
    String track = null, signature = null, finance_approved = null;

    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {

        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
//if user is admin show adminHBox and hide all other HBoxes
        if(Constants.CURRENT_USER_TYPE.equals("Admin")){
            adminHBox.setVisible(true);
            fypHBox.setVisible(false);
            financeHBox.setVisible(false);
            directorHBox.setVisible(false);
        }else if (Constants.CURRENT_USER_TYPE.equals("Fyp")) {
            adminHBox.setVisible(false);
            fypHBox.setVisible(true);
            financeHBox.setVisible(false);
            directorHBox.setVisible(false);
        }
        else if (Constants.CURRENT_USER_TYPE.equals("Finance")) {
            adminHBox.setVisible(false);
            fypHBox.setVisible(false);
            financeHBox.setVisible(true);
            directorHBox.setVisible(false);
        }
        else if (Constants.CURRENT_USER_TYPE.equals("Director")) {
            adminHBox.setVisible(false);
            fypHBox.setVisible(false);
            financeHBox.setVisible(false);
            directorHBox.setVisible(true);
        }
        String query = "";
        int var = Integer.parseInt(Constants.token_id);
        query = "SELECT * FROM request WHERE Token = " + var;

        ResultSet rs = db.executeQuery(query);
        try {
            while (rs.next()) {
                studentID_label.setText(rs.getString("StudentID"));
                studentName_label.setText(rs.getString("Student_Name"));
                RStatus_label.setText(rs.getString("RStatus"));
                RTrack_label.setText(rs.getString("Track"));
                //set decision_finance to waiting... if it is null or empty
                if(rs.getString("Decision_finance") == null)
                    financeDes_label.setText("Waiting...");
                else
                    financeDes_label.setText(rs.getString("Decision_finance"));

                //set comment_finance to waiting... if it is null or empty
                if(rs.getString("Comment_finance") == null)
                    financeCom_label.setText("Waiting...");
                else
                    financeCom_label.setText(rs.getString("Comment_finance"));

                //set decision_fyp to waiting... if it is null or empty
                if(rs.getString("Decision_fyp") == null)
                    FYPDes_label.setText("Waiting...");
                else
                    FYPDes_label.setText(rs.getString("Decision_fyp"));

                //set comment_fyp to waiting... if it is null or empty
                if(rs.getString("Comment_fyp") == null)
                    FYPCom_label.setText("Waiting...");
                else
                    FYPCom_label.setText(rs.getString("Comment_fyp"));
                if(rs.getString("admin_signature") == null) {
                    admin_signature.setText("Not Added");
                 //   signature = "Not Added";
                }
                else {
                    admin_signature.setText(rs.getString("admin_signature"));
                 //   signature = rs.getString("admin_signature");
                }
                if(rs.getString("hod_signature") == null)
                    hod_signature.setText("Not Added");
                else
                    hod_signature.setText(rs.getString("hod_signature"));
                track = rs.getString("Track");
                /*if(rs.getString("decision_finance") == null || !rs.getString("decision_finance").equalsIgnoreCase("Approved"))
                    finance_approved = "Not Approved";
                else
                    finance_approved = rs.getString("decision_finance");*/


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disableButtons();
       /* if(!track.equalsIgnoreCase("admin") || finance_approved.equalsIgnoreCase("Approved")) {
            admin_sendFypButton.setDisable(true);
            admin_sendFypButton.setText("Sent To Fyp");
        }
        else {
            admin_sendFypButton.setDisable(false);
            admin_sendFypButton.setText("Send to Fyp");
        }
        if(admin_sendFypButton.isDisable() && !admin_signature.getText().equalsIgnoreCase("Added") ) {
            admin_addSignatureButton.setDisable(false);
            admin_addSignatureButton.setText("Add Signature");
        }
        else {
            admin_addSignatureButton.setDisable(true);
                admin_addSignatureButton.setText("Signature Added");
        }*/
    }

public void fypApproveButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Decision_fyp = 'Approved', comment_fyp= 'Approved' , fyp_id ='"+Constants.CURRENT_USER_ID+"' WHERE Token = " + Constants.token_id;
         db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");
    }
    public void fypRejectButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Decision_fyp = 'Rejected' , comment_fyp= 'Rejected' ,fyp_id ='"+Constants.CURRENT_USER_ID+"' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");

    }
    public void financeApproveButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Decision_finance = 'Approved', comment_finance= 'Approved' ,finance_id ='"+Constants.CURRENT_USER_ID+"' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");
    }
    public void financeRejectButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Decision_finance = 'Rejected', comment_finance= 'Rejected' ,finance_id ='"+Constants.CURRENT_USER_ID+"' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");
    }
    public void directorAddSignature_onAction(ActionEvent actionEvent) {

        String query = "UPDATE request SET hod_signature = 'Added' , RStatus='Processed', track='admin' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");
    }
    public void admin_sendFypButton_onAction(ActionEvent actionEvent) {

        String query = "UPDATE request SET track = 'Fyp' , adminId= '"+Constants.CURRENT_USER_ID+"' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");


    }
    public void admin_addSignature_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET admin_signature = 'Added' , track = 'director' WHERE token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");
    }
    public void finance_sendToAdminButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Track = 'admin' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");

    }
    public void sendToFinanceButton_onAction(ActionEvent actionEvent) {
        String query = "UPDATE request SET Track = 'finance' WHERE Token = " + Constants.token_id;
        db.executeUpdate(query);
        pageLoader("requestDetails_main_page.fxml");

    }
    public void disableButtons(){
        if(financeDes_label.getText().equalsIgnoreCase("Approved") || financeDes_label.getText().equalsIgnoreCase("Rejected")){
            financeApproveButton.setDisable(true);
            financeRejectButton.setDisable(true);
        }
        if(FYPDes_label.getText().equalsIgnoreCase("Approved") || FYPDes_label.getText().equalsIgnoreCase("Rejected")){
            fypApproveButton.setDisable(true);
            fypRejectButton.setDisable(true);
        }
        if(admin_signature.getText().equalsIgnoreCase("Added") || financeDes_label.getText().equalsIgnoreCase("Rejected")
                || FYPDes_label.getText().equalsIgnoreCase("Rejected")){
            admin_addSignatureButton.setDisable(true);
        }
        if(hod_signature.getText().equalsIgnoreCase("Added")|| financeDes_label.getText().equalsIgnoreCase("Rejected")
                || FYPDes_label.getText().equalsIgnoreCase("Rejected")){
            directorAddSignature.setDisable(true);
        }

        if(RTrack_label.getText().equalsIgnoreCase("admin")&& !financeDes_label.getText().equalsIgnoreCase("Approved")){
            admin_sendFypButton.setDisable(false);
            admin_addSignatureButton.setDisable(false);
        }
        else{
            admin_sendFypButton.setDisable(true);
            if(!admin_signature.getText().equalsIgnoreCase("Added")){
                admin_addSignatureButton.setDisable(false);
            }
            else
            admin_addSignatureButton.setDisable(true);
        }

    }
}
