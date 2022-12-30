package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FypMainPage extends OneStop {
    @FXML
    Label navName;

    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
    }
}
