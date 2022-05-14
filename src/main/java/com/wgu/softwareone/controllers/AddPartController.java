package com.wgu.softwareone.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartController {

    @FXML
    TextField partName;
    @FXML
    TextField partInventory;
    @FXML
    TextField partCost;
    @FXML
    TextField partMin;
    @FXML
    TextField partMax;
    @FXML
    TextField partMachineOrCompanyName;

    @FXML
    Button savePartButton;
    @FXML
    Button cancelPartButton;

    @FXML
    private void changeMachineOrCompanyName(ActionEvent event) {

    }

    @FXML
    private void saveNewPart(ActionEvent event) {

    }

    @FXML
    private void cancelAddPart(ActionEvent event) {
        Stage stage = (Stage)cancelPartButton.getScene().getWindow();
        stage.close();
    }
}
