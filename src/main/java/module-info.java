

module com.example.xoserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires org.json;


    opens com.example.xoserver to javafx.fxml;
    exports com.example.xoserver;
}