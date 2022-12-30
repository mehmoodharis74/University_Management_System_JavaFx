package com.example.oneStop.Controller;

import com.example.oneStop.Classes.OneStop;
import com.example.oneStop.Classes.Transcript_SemesterRecord;
import com.example.oneStop.Classes.database;
import com.example.oneStop.Constants.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranscriptPage extends OneStop {

    @FXML
    Label navName;
    @FXML
    ScrollPane mainScrollPane;

    String maxQuery ="select max(sem_number) as max from transcript t inner join transcript_semester s on t.tid=s.transcriptId where studentID='"+Constants.CURRENT_USER_ID+"'";
    private static final database db;

    static {
        try {
            db = new database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ObservableList setTableData(int semNum)  {
        String query = "select * from transcript t inner join transcript_semester s on t.tid=s.transcriptId where studentID='"+Constants.CURRENT_USER_ID+"' AND sem_number='"+semNum+"'";

        ResultSet rs = db.executeQuery(query);
        //take data token from database and insert into table
        ObservableList<Transcript_SemesterRecord> data = FXCollections.observableArrayList();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                data.add(new Transcript_SemesterRecord(rs.getString("subjects"), rs.getString("credits"),
                        rs.getString("points"), rs.getString("grade")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return data;
    }

    public void initialize(){
        if(!Constants.CURRENT_USER_NAME.isEmpty())
            navName.setText(Constants.CURRENT_USER_NAME);
        VBox vbox = new VBox();
        for(int i=0; i<getMaxSemester(maxQuery); i++){
            TableView table = new TableView();
            TableColumn<Transcript_SemesterRecord, String> credits = new TableColumn<>("Course");
            TableColumn<Transcript_SemesterRecord, String> grades = new TableColumn<>("Credits");
            TableColumn<Transcript_SemesterRecord, String> points = new TableColumn<>("Points");
            TableColumn<Transcript_SemesterRecord, String> courses = new TableColumn<>("Grade");

            credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
            grades.setCellValueFactory(new PropertyValueFactory<>("grades"));
            points.setCellValueFactory(new PropertyValueFactory<>("points"));
            courses.setCellValueFactory(new PropertyValueFactory<>("courses"));

            //add columns to table
            table.getColumns().addAll(credits,grades,points,courses);
            table.setItems(setTableData(i + 1));
            //set table columnResizePolicy to constrained resize policy
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            //set table height to 200
            table.setPrefHeight(200);

            Label label = new Label("Semester "+(i+1));
            //set label to bold
            label.setFont(new Font("Arial", 14));

            //set margin on top
            vbox.setMargin(label, new javafx.geometry.Insets(10,0,10,0));
            vbox.getChildren().addAll(label,table);

            credits.setStyle("-fx-alignment: CENTER;");
            grades.setStyle("-fx-alignment: CENTER;");
            points.setStyle("-fx-alignment: CENTER;");
            courses.setStyle("-fx-alignment: CENTER;");
            //create new label at the middle of the sceen of text Semester i+1


        }
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        mainScrollPane.setContent(vbox);
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setPadding(new javafx.geometry.Insets(20,20,20,20));


    }
}
