package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Classes.database;
import com.example.oneStop.Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackActivityMainPage extends OneStop {
    @FXML
    Label navName;

    @FXML
    Label studentID_label;
    @FXML
    Label RTrack_label;
    @FXML
    Label RStatus_label;
    @FXML
    Label R_start_time;
    @FXML
    Label R_completion_time;
    @FXML
    Label financeDes_label;
    @FXML
    Label financeCom_label;
    @FXML
    Label FYPDes_label;
    @FXML
    Label FYPCom_label;

    @FXML
    Label Admin_sig_label;

    @FXML
    Label HOD_sig_label;

    private static final database db;

    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        if (!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        String query;
        query = "SELECT * FROM request WHERE StudentId = " + Constants.CURRENT_USER_ID;
        ResultSet rs = db.executeQuery(query);
        try {
            while (rs.next()) {
                studentID_label.setText(rs.getString("StudentID"));
                R_start_time.setText(rs.getString("S_Time"));
                if(rs.getString("RStatus").equalsIgnoreCase("Completed")){
                    R_completion_time.setText(rs.getString("E_Time"));
                }
                else
                    R_completion_time.setText("Not Completed Yet");
                RStatus_label.setText(rs.getString("RStatus"));
                RTrack_label.setText(rs.getString("Track"));
                if(rs.getString("Decision_finance") == null)
                    financeDes_label.setText("Waiting...");
                else
                    financeDes_label.setText(rs.getString("Decision_finance"));

                //set comment_finance to waiting... if it is null or empty
                if(rs.getString("Comment_finance") == null)
                    financeCom_label.setText("Waiting...");
                else
                    financeCom_label.setText(rs.getString("Comment_finance"));

                if(rs.getString("Decision_fyp") == null)
                    FYPDes_label.setText("Waiting...");
                else
                    FYPDes_label.setText(rs.getString("Decision_fyp"));

                if(rs.getString("Comment_fyp") == null)
                    FYPCom_label.setText("Waiting...");
                else
                    FYPCom_label.setText(rs.getString("Comment_fyp"));
                if(rs.getString("admin_signature") == null) {
                    Admin_sig_label.setText("Not Added");

                }
                else {
                    Admin_sig_label.setText(rs.getString("admin_signature"));

                }
                if(rs.getString("hod_signature") == null)
                    HOD_sig_label.setText("Not Added");
                else
                    HOD_sig_label.setText(rs.getString("hod_signature"));

            }


        } catch (SQLException e) {
            e.printStackTrace();


        }
    }


}
