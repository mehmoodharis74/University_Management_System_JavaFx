package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Constants.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ApplyDegreeDetail extends OneStop {
@FXML
Button logoutButton, SubmitDegreeBtn;
    @FXML
    Label navName,degree_errorLabel;
    @FXML
    TextField degree_studentID, degree_studentName, degree_studentFname, degree_studentAddress, degree_studentEmail, degree_major, degree_campus, degree_cgpa;




    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty()) {
            navName.setText(Constants.CURRENT_USER_NAME);
            degree_studentID.setText(Constants.CURRENT_USER_ID.toString());
        }
    }
    public void SubmitDegreeBtn_onAction(ActionEvent actionEvent)  {
        if(validInputs()){
            submit_degree_request(degree_studentName.getText(),degree_studentFname.getText(),degree_studentAddress.getText(),
                    degree_studentEmail.getText(),degree_major.getText(),degree_campus.getText(),degree_cgpa.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Degree Request");
            alert.setHeaderText("Degree Request");
            alert.setContentText("Your Degree Request has been submitted successfully");
            alert.showAndWait();
            pageLoader("student_main_page.fxml");
        }

    }

    private boolean validInputs() {
        if(degree_studentID.getText().isEmpty() || degree_studentName.getText().isEmpty() || degree_studentFname.getText().isEmpty() || degree_studentAddress.getText().isEmpty() || degree_studentEmail.getText().isEmpty() || degree_major.getText().isEmpty() || degree_campus.getText().isEmpty() || degree_cgpa.getText().isEmpty()){
            degree_errorLabel.setText("Please fill all the fields");
            return false;
        }
        double cgpa = Double.parseDouble(degree_cgpa.getText());

        //if degree_studentName has some numbers or special characters then return false

        //if degree_studentFname has some numbers or special characters then return false
        if(!degree_studentEmail.getText().contains("@")){
            degree_errorLabel.setText("Please enter a valid email");
            return false;
        }
        else if(!(degree_major.getText().equalsIgnoreCase("CS") || degree_major.getText().equalsIgnoreCase("EE") || degree_major.getText().equalsIgnoreCase("AI")
                || degree_major.getText().equalsIgnoreCase("DS") || degree_major.getText().equalsIgnoreCase("SE")
                || degree_major.getText().equalsIgnoreCase("BBA")|| degree_major.getText().equalsIgnoreCase("CSY"))){
            degree_errorLabel.setText("Please enter a valid major");
            return false;
        }
        else if(!(degree_campus.getText().equalsIgnoreCase("karachi") || degree_campus.getText().equalsIgnoreCase("Islamabad") || degree_campus.getText().equals("lahore")|| degree_campus.getText().equals("peshawar") || degree_campus.getText().equals("faisalabad"))){
            degree_errorLabel.setText("Please enter a valid campus");
            return false;
        }
        else if(cgpa<0 || cgpa>4){
            degree_errorLabel.setText("Please enter a valid CGPA");
            return false;
        }
        return true;
    }
}
