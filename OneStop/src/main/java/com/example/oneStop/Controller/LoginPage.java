package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginPage extends OneStop {
    @FXML Button login_page_loginBtn;
    @FXML Label errorLabel;
    @FXML TextField login_email_input_field, login_password_input_field;
    @FXML ComboBox<String> user_choice_box;

    private final String[] users = {"Student", "Admin", "FypCommittee", "FinanceCommittee", "Director"};

    public void initialize() {
        user_choice_box.setItems(FXCollections.observableArrayList(users));
    }
      public void selectUserAction() {
          user_choice_box.setPromptText(user_choice_box.getValue());
      }
    public void loginBtn_onAction(){
        if(validDataInput()) {
            if(verifyUserLogin(login_email_input_field.getText(), login_password_input_field.getText(), user_choice_box.getValue())){
                if(user_choice_box.getValue().equals("Student")){
                    pageLoader("student_main_page.fxml");

                }
                else if(user_choice_box.getValue().equals("Admin")){
                    pageLoader("admin_main_page.fxml");
                }
                else if(user_choice_box.getValue().equals("FypCommittee")){
                    pageLoader("fyp_main_page.fxml");
                }
                else if(user_choice_box.getValue().equals("FinanceCommittee")){
                    pageLoader("finance_main_page.fxml");
                }
                else if(user_choice_box.getValue().equals("Director")){
                    pageLoader("director_main_page.fxml");
                }
            }
            else{
                errorLabel.setText("Invalid Username or Password");
            }
        }


    }
    public boolean validDataInput() {
       if (login_email_input_field.getText().isEmpty()) {
                errorLabel.setText("Please enter Email");
                return false;
            }
       else if(login_password_input_field.getText().isEmpty()) {
           errorLabel.setText("Please enter Password");
           return false;
       }
        else if (user_choice_box.getValue() == null) {
            errorLabel.setText("Please select a user");
            return false;
        }
        return true;
    }
}
