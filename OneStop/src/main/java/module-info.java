module com.example.onestop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.oneStop to javafx.fxml;
    exports com.example.oneStop;
    exports com.example.oneStop.Controller;
    opens com.example.oneStop.Controller to javafx.fxml;
    exports com.example.oneStop.Classes;
    opens com.example.oneStop.Classes to javafx.fxml;
}