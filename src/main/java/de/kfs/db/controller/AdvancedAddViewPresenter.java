package de.kfs.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class AdvancedAddViewPresenter {

    public static String fxml = "/fxml/advancedAddView.fxml";
    @FXML
    public CheckBox backPedalBrake;
    @FXML
    public ChoiceBox engineChocie;
    @FXML
    public Button advancedAddButton;


    /**
     * Handles the addition of an E-Bike to the datastructure
     * @param actionEvent
     */
    public void onAdvanceAddButtonPressed(ActionEvent actionEvent) {
    }
}
