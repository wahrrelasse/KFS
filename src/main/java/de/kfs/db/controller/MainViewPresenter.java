package de.kfs.db.controller;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;

public class MainViewPresenter {

    public static String fxml = "/fxml/mainView.fxml";

    /**
     * Opens up save Dialogue and saves Data from table
     *
     */

    public void onSaveButtonPressed(ActionEvent actionEvent) {
    }

    /**
     * Opens up the edit Window
     * @param actionEvent
     */
    public void onEditButtonPressed(ActionEvent actionEvent) {
    }

    /**
     * Opens up delete Window
     * @param actionEvent
     */
    public void onDeleteButtonPressed(ActionEvent actionEvent) {
    }

    /**
     * enables/disables filtering of E-Bikes
     * @param actionEvent
     */
    public void onOnlyEBikeChecked(ActionEvent actionEvent) {
    }

    /**
     * Begins searching of whatever is specified in the corresponding
     * choicebox
     * @param keyEvent
     */
    public void onSearchFieldKeyReleased(KeyEvent keyEvent) {
    }

    /**
     * Adds the data from the textfield to a Bike Object which
     * will then be added into the table
     * @param actionEvent
     */
    public void onAddButtonPressed(ActionEvent actionEvent) {
    }

    /**
     * Opens up the Advanced Add View, where E-Bikes will be added
     * but there are more parameters to fill in
     * @param actionEvent
     */
    public void onAdvancedButtonPressed(ActionEvent actionEvent) {
    }
}
