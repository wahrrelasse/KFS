package de.kfs.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteViewPresenter extends AbstractPresenter{

    public static String fxml = "/fxml/deleteView.fxml";
    @FXML
    public TextField deleteNumberField;
    @FXML
    public Button confirmDeleteButton;

    /**
     * deletes the Bike specified by internalNumber out of the textField
     *
     * @param actionEvent
     */
    public void onDeleteButtonPressed(ActionEvent actionEvent) {
    }
}
