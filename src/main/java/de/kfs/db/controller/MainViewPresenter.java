package de.kfs.db.controller;

import de.kfs.db.structure.AbstractBike;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.checkerframework.checker.units.qual.A;

public class MainViewPresenter {

    public static String fxml = "/fxml/mainView.fxml";



    @FXML
    public TableView table;
    @FXML
    public TableColumn numCol;
    @FXML
    public TableColumn frameKeyCol;
    @FXML
    public TableColumn bpKeyCol;
    @FXML
    public TableColumn frameNumCol;
    @FXML
    public TableColumn extraInfoCol;

    @FXML
    public CheckBox onlyECheck;
    @FXML
    public ChoiceBox searchChoice;
    @FXML
    public TextField searchField;
    @FXML
    public TextField internalField;
    @FXML
    public TextField frameField;
    @FXML
    public TextField colorField;

    @FXML
    public TextField extraField;
    @FXML
    public TextField keyField;
    @FXML
    public TextField brandField;


    @FXML
    public void initialize() {
        numCol.setCellValueFactory(new PropertyValueFactory<AbstractBike, String>("number"));
        frameKeyCol.setCellValueFactory(new PropertyValueFactory<AbstractBike, String>("frameKey"));
        bpKeyCol.setCellValueFactory(new PropertyValueFactory<AbstractBike, String>("bpKey"));
        frameNumCol.setCellValueFactory(new PropertyValueFactory<AbstractBike, String>("frameNumber"));
        extraInfoCol.setCellValueFactory(new PropertyValueFactory<AbstractBike, String>("info"));
    }

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
