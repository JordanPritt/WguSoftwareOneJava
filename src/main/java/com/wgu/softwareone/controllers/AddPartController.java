package com.wgu.softwareone.controllers;

import com.wgu.softwareone.models.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.UUID;

public class AddPartController {

    @FXML
    Label machineOrCompanyNameLabel;
    @FXML
    ToggleGroup machineNameGroup;

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
    RadioButton inHouseRadio;
    @FXML
    RadioButton machineIdRadio;

    @FXML
    private void changeMachineOrCompanyName(ActionEvent event) {
        if (inHouseRadio.isSelected() == true) {
            machineOrCompanyNameLabel.setText("Machine ID");
        } else {
            machineOrCompanyNameLabel.setText("Company Name");
        }
    }

    @FXML
    private void saveNewPart(ActionEvent event) {
        try {
            Part newPart = new Part(
                    generateId(),
                    partName.getText(),
                    Double.parseDouble(partCost.getText()),
                    Integer.parseInt(partInventory.getText()),
                    Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText())
            );

            MainController.AddPart(newPart);
            closeStage();
        } catch(Exception ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect Value");
            errorAlert.setContentText("Sorry, one or more of the values you entered were incorrect. Please try again " +
                    "after carefully looking over the entry.");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void cancelAddPart(ActionEvent event) {
        closeStage();
    }

    private int generateId() {
        int newID = MainController.nextPartId();
        return newID;
    }

    private void closeStage() {
        Stage stage = (Stage) cancelPartButton.getScene().getWindow();
        stage.close();
    }
}
