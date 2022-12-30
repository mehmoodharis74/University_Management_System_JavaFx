package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GenerateFeeInstallment extends OneStop {
    @FXML
    Label navName;
    @FXML
    TextField installmentStudentId;
    @FXML
    TextArea feeInstallmentTextArea;
    @FXML
    ComboBox feeInstallmentChoiceBox;

    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        feeInstallmentChoiceBox.getItems().addAll( "1 Installment", "2 Installments", "3 Installments");
        installmentStudentId.setText(Constants.CURRENT_USER_ID.toString());
        installmentStudentId.setEditable(false);
    }
    public void feeInstallmentChoiceBox_onAction(){
        feeInstallmentChoiceBox.setValue(feeInstallmentChoiceBox.getValue());
    }
    public void submitInstallmentButton_onAction(){
       // generate alert box of information of request submitted and navigate to home page
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your request has been submitted successfully!");
        alert.showAndWait();
        generateInstallmentRequest();
        pathIndex--;
        pageLoader(path[pathIndex-1]);


    }


}
