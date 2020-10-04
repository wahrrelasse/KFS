package de.kfs.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EditViewPresenter {


    public static String fxml = "/fxml/editView.fxml";
    @FXML
    public Button confirmEditButton;

    /**
     * Handles the editing of a bike Object with new Data gathered from textfields
     * @param actionEvent
     */
    public void onConfirmEditButtonPressed(ActionEvent actionEvent) {
    }
}
