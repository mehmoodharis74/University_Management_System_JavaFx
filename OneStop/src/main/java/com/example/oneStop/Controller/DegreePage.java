package com.example.oneStop.Controller;

import com.example.oneStop.Classes.Degree;
import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DegreePage extends OneStop {
    @FXML
    Label navName, viewDegreeStudentName, viewDegreeName;

    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        Degree degree = loadDegree();
        if(degree != null){
            viewDegreeStudentName.setText(degree.getStudentName());
            viewDegreeName.setText(degree.getDegreeName());
        }
    }
}
