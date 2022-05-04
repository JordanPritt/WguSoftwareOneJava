module com.wgu.softwareone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wgu.softwareone to javafx.fxml;
    exports com.wgu.softwareone;

    opens com.wgu.softwareone.controllers to javafx.fxml;
    exports com.wgu.softwareone.controllers;

    opens com.wgu.softwareone.models to javafx.fxml;
    exports com.wgu.softwareone.models;
}