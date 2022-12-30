package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StudentMainPage extends OneStop {
    @FXML
    Button logoutButton, applyForDegreeBtn, applyForComplainBtn, trackActivityBtn, viewDegreeBtn, applyForTranscriptBtn, viewTranscriptBtn;
@FXML
Label navName;
@FXML
    VBox findDegreeVBox,ActivityTrackingVBox,ApplyForDegreeVBox;

    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        //if request is completed and hod_signature is Approved from database then findDegreeVBox will be visible
        if(getDegreeRequestStatus(String.valueOf(Constants.CURRENT_USER_ID))){
            ApplyForDegreeVBox.setDisable(true);
            findDegreeVBox.setDisable(true);
            ActivityTrackingVBox.setDisable(false);

        }
        else {
            ApplyForDegreeVBox.setDisable(false);
            findDegreeVBox.setDisable(true);
            ActivityTrackingVBox.setDisable(true);
        }
        if(isDegreeSaved(String.valueOf(Constants.CURRENT_USER_ID))){
            findDegreeVBox.setDisable(false);
        }
        else{
            findDegreeVBox.setDisable(true);
        }
//        ApplyForDegreeVBox.setDisable(false);
//        ActivityTrackingVBox.setDisable(true);
    }

}
