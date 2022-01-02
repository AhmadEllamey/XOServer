

module com.example.xoserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.example.xoserver to javafx.fxml;
    exports com.example.xoserver;
}