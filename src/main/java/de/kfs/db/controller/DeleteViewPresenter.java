package de.kfs.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DeleteViewPresenter {

    public static String fxml = "/fxml/deleteView.fxml";
    @FXML
    public int deleteNumberField;

    /**
     * deletes the Bike specified by internalNumber out of the textField
     * @param actionEvent
     */
    public void onDeleteButtonPressed(ActionEvent actionEvent) {
    }
}
