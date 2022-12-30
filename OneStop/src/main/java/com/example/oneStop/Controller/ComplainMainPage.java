package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Classes.database;
import com.example.oneStop.Constants.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class ComplainMainPage extends OneStop {
    @FXML
    TextField firstTextID;
    @FXML
    TextField secondTextID;
    @FXML
    TextField thirdTextID;

    @FXML
    Label navName;

    @FXML
    TextField complainStudentIDLabel;

    @FXML
    TextField complainStudentNameLabel;


    public void submitComplainButton_onAction(ActionEvent actionEvent) {
    }

    @FXML
    ComboBox<String> complain_combobox;

    private String users[] = {"SelectAll", "Name", "Campus", "Degree"};

    public void initialize() {
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        complain_combobox.setItems(FXCollections.observableArrayList(users));
        complainStudentIDLabel.setText(Constants.CURRENT_USER_ID.toString());
        complainStudentNameLabel.setText(Constants.CURRENT_USER_NAME);
        complain_combobox.setValue(users[0]);
    }
    private static database db;

    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void submitComplainButton_onActionButton_onAction(ActionEvent actionEvent) {
        String query = "";
        String name = firstTextID.getText().toUpperCase();
        String campus = secondTextID.getText().toUpperCase();
        String degree = thirdTextID.getText().toUpperCase();
        if (complain_combobox.getValue().equals("Name"))
        {
            query = "UPDATE degree SET Student_Name = '" + name + "' WHERE StudentID = " + Constants.CURRENT_USER_ID;
            db.executeUpdate(query);
            pageLoader("student_main_page.fxml");
        }
        else if (complain_combobox.getValue().equals("Campus"))
        {
            query = "UPDATE degree SET location = '" + campus + "' WHERE StudentID = " + Constants.CURRENT_USER_ID;
            db.executeUpdate(query);
            pageLoader("student_main_page.fxml");
        }
        else if (complain_combobox.getValue().equals("Degree"))
        {
            query = "UPDATE degree SET degree_name = '" + degree + "' WHERE StudentID = " + Constants.CURRENT_USER_ID;
            db.executeUpdate(query);
            pageLoader("student_main_page.fxml");
        }
        else if (complain_combobox.getValue().equals("SelectAll"))
        {
            query = "UPDATE degree SET Student_Name = '" + name + "', location = '" + campus + "', Degree_name = '" + degree + "' WHERE StudentID = " + Constants.CURRENT_USER_ID;
            db.executeUpdate(query);
            pageLoader("student_main_page.fxml");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complain Request");
        alert.setHeaderText("Complain Request");
        alert.setContentText("Your Data has been change successfully\n Find Your Degree");
        alert.showAndWait();
    }

    public void complainComboBox_btn(ActionEvent actionEvent) {
        if(complain_combobox.getValue().toString() == "Name"){
            firstTextID.setVisible(true);
            firstTextID.setPromptText("Enter Your Name");
            secondTextID.setVisible(false);
            thirdTextID.setVisible(false);
        }
        if(complain_combobox.getValue().toString() == "Degree"){
            firstTextID.setVisible(true);
            firstTextID.setPromptText("Enter Degree");
            secondTextID.setVisible(false);
            thirdTextID.setVisible(false);
        }
        if(complain_combobox.getValue().toString() == "Campus"){
            firstTextID.setVisible(true);
            firstTextID.setPromptText("Enter Campus");
            thirdTextID.setVisible(false);
            secondTextID.setVisible(false);
        }
        if(complain_combobox.getValue().toString() == "SelectAll"){
            firstTextID.setVisible(true);
            firstTextID.setPromptText("Enter Your Name");
            secondTextID.setVisible(true);
            secondTextID.setPromptText("Enter Campus");
            thirdTextID.setVisible(true);
            thirdTextID.setPromptText("Enter Degree");
        }


    }
}
