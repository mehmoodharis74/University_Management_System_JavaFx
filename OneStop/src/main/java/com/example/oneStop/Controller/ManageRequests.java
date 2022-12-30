package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Classes.Requests;
import com.example.oneStop.Classes.database;
import com.example.oneStop.Constants.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageRequests extends OneStop {

    @FXML
    private TableView<Requests> requestTable;
    @FXML
    private TableColumn<Requests, String> requestDegree;

    @FXML
    private TableColumn<Requests, String> requestName;

    @FXML
    private TableColumn<Requests, Integer> studentId;

    @FXML
    private TableColumn<Requests, Integer> tokenId;
    @FXML
    private TableColumn<Requests, String> degreeIssued;
    @FXML
    Label navName;

    @FXML
    ComboBox<String> requestsChoiceBox;
    @FXML
    TextField tokenInput;
    @FXML
    HBox manageRequestHBox, issueDegreeHBox, issueTranscriptHBox;
    @FXML
    TextField issueDegreeToken, issueTranscriptToken;
    @FXML
    Button issueDegreeButton, issueTranscriptButton;

    private String users[] = {"All Requests", "Pending", "Approved", "Rejected"};


    private static database db;

    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String query = "SELECT * FROM request";


    public ObservableList setTableData()  {

        ResultSet rs = db.executeQuery(query);
        //take data token from database and insert into table
        ObservableList<Requests> data = FXCollections.observableArrayList();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (Constants.ADMIN_PRESSED_BUTTON.equalsIgnoreCase("issueTranscript")) {
                    data.add(new Requests(rs.getInt("tid"), rs.getInt("StudentID"),
                            getStudentName(String.valueOf(rs.getInt("StudentID"))), "Transcript", "No"));
                }
            else{
                data.add(new Requests(rs.getInt("Token"), rs.getInt("StudentID"),
                        rs.getString("Student_Name"), rs.getString("Degree"), rs.getString("degree_issued")));}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return data;
    }
public boolean validToken(String token){
        return db.checkToken(token);
}
    public void req_DetailsBtn(ActionEvent actionEvent) {
        //check tokenInput is integer or not
       if(validToken(tokenInput.getText())){
                    Constants.token_id = tokenInput.getText();
                    //go to request detail page
                    pageLoader("requestDetails_main_page.fxml");
                }
                else{
                    //show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Token not found");
                    alert.setContentText("Please enter a valid token");
                    alert.showAndWait();
                }

       /* Constants.token_id =tokenInput.getText();
        pageLoader("requestDetails_main_page.fxml");*/
    }

    public void initialize()  {
        if(Constants.CURRENT_USER_TYPE.equalsIgnoreCase("admin")){
            if(Constants.ADMIN_PRESSED_BUTTON.equalsIgnoreCase("issueDegree")){
                manageRequestHBox.setVisible(false);
                issueDegreeHBox.setVisible(true);
                issueTranscriptHBox.setVisible(false);
                requestsChoiceBox.setVisible(false);
                degreeIssued.setVisible(true);
                query = "SELECT * FROM request WHERE rStatus = 'Processed' AND Degree_Issued = 'No'";
            }
            else if(Constants.ADMIN_PRESSED_BUTTON.equalsIgnoreCase("issueTranscript")){
                manageRequestHBox.setVisible(false);
                issueDegreeHBox.setVisible(false);
                issueTranscriptHBox.setVisible(true);
                requestsChoiceBox.setVisible(false);
                query = "SELECT * FROM transcript where granted = 'No'";
            //    query = "select * from transcript t,student s where t.studentId='" +Constants.+ s.id and t.requested='YES'";
            }
            else{
                manageRequestHBox.setVisible(true);
                issueDegreeHBox.setVisible(false);
                issueTranscriptHBox.setVisible(false);
                requestsChoiceBox.setVisible(true);

            }
        }
        requestsChoiceBox.setItems(FXCollections.observableArrayList(users));
        //setting name in nav bar to current user
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        tokenId.setCellValueFactory(new PropertyValueFactory<Requests,Integer>("tokenId"));
        studentId.setCellValueFactory(new PropertyValueFactory<Requests,Integer>("studentId"));
        requestName.setCellValueFactory(new PropertyValueFactory<Requests,String>("requestName"));
        requestDegree.setCellValueFactory(new PropertyValueFactory<Requests,String>("requestDegree"));
        degreeIssued.setCellValueFactory(new PropertyValueFactory<Requests,String>("degreeIssued"));



        requestTable.setItems(setTableData());
        //align values in columns to center
        tokenId.setStyle("-fx-alignment: CENTER;");
        studentId.setStyle("-fx-alignment: CENTER;");
        requestName.setStyle("-fx-alignment: CENTER;");
        requestDegree.setStyle("-fx-alignment: CENTER;");
        degreeIssued.setStyle("-fx-alignment: CENTER;");

    }


    public void manageRequestComboBoxBtn(ActionEvent actionEvent) {
        if(Constants.CURRENT_USER_TYPE.equalsIgnoreCase("admin")
                ||Constants.CURRENT_USER_TYPE.equalsIgnoreCase("director") ) {
            if (requestsChoiceBox.getValue().equals("Pending"))
                query = "SELECT * FROM request WHERE RStatus = 'Pending'";
            else if (requestsChoiceBox.getValue().equals("Rejected"))
                query = "SELECT * FROM request WHERE RStatus = 'Rejected'";
            else if(requestsChoiceBox.getValue().equals("All Requests"))
                query = "SELECT * FROM request";
            else
                query = "SELECT * FROM request WHERE RStatus <> 'Pending' or RStatus <> 'Approved' or RStatus <> 'Rejected'";
        }
        else if(Constants.CURRENT_USER_TYPE.equalsIgnoreCase("fyp")) {
            if (requestsChoiceBox.getValue().equals("Pending"))
                query = "SELECT * FROM request WHERE RStatus = 'Pending' and track = 'FYP'";
            else if (requestsChoiceBox.getValue().equals("Approved"))
                query = "SELECT * FROM request WHERE decision_fyp = 'Approved' and fyp_id = '"+Constants.CURRENT_USER_ID+"'";
            else if (requestsChoiceBox.getValue().equals("Rejected"))
                query = "SELECT * FROM request WHERE  decision_fyp = 'Rejected and fyp_id = '"+Constants.CURRENT_USER_ID+"'";
            else
                query = "SELECT * FROM request where track = 'FYP'";
        }
        else if(Constants.CURRENT_USER_TYPE.equalsIgnoreCase("finance")) {
            if (requestsChoiceBox.getValue().equals("Pending"))
                query = "SELECT * FROM request WHERE RStatus = 'Pending' and track = 'finance'";
            else if (requestsChoiceBox.getValue().equals("Approved"))
                query = "SELECT * FROM request WHERE decision_finance = 'Approved' and finance_id = '"+Constants.CURRENT_USER_ID+"'";
            else if (requestsChoiceBox.getValue().equals("Rejected"))
                query = "SELECT * FROM request WHERE  decision_finance = 'Rejected' and finance_id = '"+Constants.CURRENT_USER_ID+"'";
            else
                query = "SELECT * FROM request where track = 'finance'";
        }
        setTableData();
        initialize();
    }
    public void issueDegreeButton_onAction(ActionEvent event){
        if(validToken(issueDegreeToken.getText()))
        if(issueDegree(issueDegreeToken.getText())) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Success");
               alert.setHeaderText("Degree Issued");
               alert.setContentText("Degree has been issued to the student");
               alert.showAndWait();
               //db.deleteDegreeRequest(issueDegreeToken.getText());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Degree not issued");
            alert.setContentText("Degree has not been issued to the student");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Token");
            alert.setContentText("Please enter a valid token");
            alert.showAndWait();
        }
        pageLoader(path[pathIndex-1]);
    }
    public void issueTranscriptButton_onAction(ActionEvent event) {
            if (issueTranscript(issueTranscriptToken.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Transcript Issued");
                alert.setContentText("Transcript has been issued to the student");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Transcript not issued");
                alert.setContentText("Transcript has not been issued to the student");
                alert.showAndWait();
            }
            pageLoader(path[pathIndex-1]);
    }

}
