package de.kfs.db.controller;



import com.google.common.eventbus.Subscribe;
import de.kfs.db.SceneManager;
import de.kfs.db.events.management.UpdateBikeEvent;
import de.kfs.db.events.table.AdvancedAddEvent;
import de.kfs.db.events.table.DeleteBikeEvent;
import de.kfs.db.events.table.EditBikeEvent;

import de.kfs.db.structure.*;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


public class MainViewPresenter extends AbstractPresenter{


    public static String fxml = "/fxml/mainView.fxml";




    @FXML
    public TableView<AbstractBike> table;
    @FXML
    public TableColumn<AbstractBike, String> numCol;
    @FXML
    public TableColumn<AbstractBike, String> frameKeyCol;
    @FXML
    public TableColumn<AbstractBike, String> bpKeyCol;
    @FXML
    public TableColumn<AbstractBike, String> frameNumCol;
    @FXML
    public TableColumn<AbstractBike, String> extraInfoCol;

    @FXML
    public CheckBox onlyECheck;
    @FXML
    public ChoiceBox<String> searchChoice;
    @FXML
    public TextField searchField;
    @FXML
    public TextField internalField;
    @FXML
    public TextField frameField;
    @FXML
    public TextField colorField;
    @FXML
    public TextField keyField;
    @FXML
    public TextField brandField;


    @FXML
    public void initialize() {
        numCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        frameKeyCol.setCellValueFactory(new PropertyValueFactory<>("frameKey"));
        bpKeyCol.setCellValueFactory(new PropertyValueFactory<>("bpKey"));
        frameNumCol.setCellValueFactory(new PropertyValueFactory<>("frameNumber"));
        extraInfoCol.setCellValueFactory(new PropertyValueFactory<>("info"));

        searchChoice.getItems().addAll("Nummer", "Schlüsselnummer", "Rahmennummer", "Weitere Informationen");
        searchChoice.setValue("Nummer");
        searchChoice.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {

            if(newVal != null) {
                searchField.setText("");
            }
        });
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
        eventBus.post(new EditBikeEvent());
    }

    /**
     * Opens up delete Window
     * @param actionEvent
     */
    public void onDeleteButtonPressed(ActionEvent actionEvent) {
        eventBus.post(new DeleteBikeEvent());
    }

    /**
     * enables/disables filtering of E-Bikes
     * @param actionEvent
     */
    public void onOnlyEBikeChecked(ActionEvent actionEvent) {
        if(onlyECheck.isSelected()) {
            bikeManagement.changePredicate(p -> p instanceof EBike);
        } else {
            bikeManagement.changePredicate(p -> true);
        }
    }

    /**
     * Begins searching of whatever is specified in the corresponding
     * choicebox
     * @param keyEvent
     */
    public void onSearchFieldKeyReleased(KeyEvent keyEvent) {

        switch (searchChoice.getValue()) {
            case "Nummer":
                bikeManagement.addPredicate(p -> ((AbstractBike) p).getInternalNumber().toLowerCase().trim().contains(searchField.getText().toLowerCase().trim()));
                break;
            case "Schlüsselnummer":
                bikeManagement.addPredicate(p -> ((AbstractBike) p).getBikeKey().getFrameKey().toLowerCase().trim().contains(searchField.getText().toLowerCase().trim()));
                break;
            case "Rahmennummer":
                bikeManagement.addPredicate(p -> ((AbstractBike) p).getFrameNumber().toLowerCase().trim().contains(searchField.getText().toLowerCase().trim()));
                break;
            case "Weitere Informationen":
                bikeManagement.addPredicate(p -> ((AbstractBike) p).getAdditionalInfo().toString().toLowerCase().trim().contains(searchField.getText().toLowerCase().trim()));
                break;
        }


    }

    /**
     * Adds the data from the textfield to a Bike Object which
     * will then be added into the table
     * @param actionEvent
     */
    public void onAddButtonPressed(ActionEvent actionEvent) {
        if (internalField.getText().isEmpty() || frameField.getText().isEmpty()) {
            SceneManager.showWarning("Daten nicht korrekt eingegben");
        } else {
            addAction();
        }

    }

    private void addAction() {

        Bike b;
        InformationWrapper info = new InformationWrapper(brandField.getText().trim(), colorField.getText().trim(), -1, -1);

        b = new Bike(internalField.getText().trim(), frameField.getText().trim(), BikeKey.createNormalBikeKey(keyField.getText()), info);
        bikeManagement.addBike(b);
        updateBikes(null);
    }

    /**
     * Opens up the Advanced Add View, where E-Bikes will be added
     * but there are more parameters to fill in
     * @param actionEvent
     */
    public void onAdvancedButtonPressed(ActionEvent actionEvent) {
        eventBus.post(new AdvancedAddEvent());
    }

    /**
     * updates the bikes in current table using the data within
     * the bikemanagement as soon as an UpdateBikeEvent is called
     *
     */
    @Subscribe
    public void updateBikes(UpdateBikeEvent event) {
        table.setItems(bikeManagement.getFlBike());
    }
}
