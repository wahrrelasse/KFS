package de.kfs.db.controller;

import de.kfs.db.SceneManager;
import de.kfs.db.controller.filter.IntegerFieldFilter;
import de.kfs.db.events.ConfirmEditEvent;
import de.kfs.db.structure.BikeKey;
import de.kfs.db.structure.InformationWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class EditViewPresenter extends AbstractPresenter{


    public static String fxml = "/fxml/editView.fxml";
    @FXML
    public TextField numberField;
    @FXML
    public TextField newKeyField;
    @FXML
    public TextField newBpKeyField;
    @FXML
    public TextField frameHField;
    @FXML
    public TextField tireDField;
    @FXML
    public TextField manufacturerField;
    @FXML
    public TextField colorField;


    @FXML
    public void initialize() {
        frameHField.setTextFormatter(new IntegerFieldFilter());
        tireDField.setTextFormatter(new IntegerFieldFilter());
    }
    /**
     * Handles the editing of a bike Object with new Data gathered from textfields
     * @param actionEvent
     */
    public void onConfirmEditButtonPressed(ActionEvent actionEvent) {
        if(!numberField.getText().trim().isEmpty()) {

            if((newKeyField.getText().trim().isEmpty() && newBpKeyField.getText().trim().isEmpty())
                    && (tireDField.getText().trim().isEmpty() && frameHField.getText().trim().isEmpty()
                    && manufacturerField.getText().trim().isEmpty() && colorField.getText().trim().isEmpty())) {
                SceneManager.showWarning("Keine Änderungen eingegebene!");
            } else {
                editAction();

            }
        } else {
            SceneManager.showWarning("Keine Radnummer eingegeben!");
        }


    }

    private void editAction() {
        //3 cases: edit only informationWrapper, edit only Key, edit both
        if(newKeyField.getText().trim().isEmpty() && newBpKeyField.getText().trim().isEmpty()) {

            bikeManagement.editBike(numberField.getText().trim(), null, checkInfo());

        } else if(tireDField.getText().trim().isEmpty() && frameHField.getText().trim().isEmpty() &&
                manufacturerField.getText().trim().isEmpty() && colorField.getText().trim().isEmpty()) {

            bikeManagement.editBike(numberField.getText().trim(), new BikeKey(newKeyField.getText().trim(), newBpKeyField.getText().trim()), null);

        } else {
            bikeManagement.editBike(numberField.getText().trim(), new BikeKey(newKeyField.getText().trim(), newBpKeyField.getText().trim()), checkInfo());
        }

        eventBus.post(new ConfirmEditEvent());
    }

    private InformationWrapper checkInfo() {
        if (tireDField.getText().trim().isEmpty()) {
            if (frameHField.getText().trim().isEmpty()) {
                return new InformationWrapper(manufacturerField.getText().trim(), colorField.getText().trim(), -1, -1);
            } else {
                return new InformationWrapper(manufacturerField.getText().trim(), colorField.getText().trim(), Integer.parseInt(frameHField.getText().trim()), -1);
            }
        } else if (frameHField.getText().trim().isEmpty()) {
            return new InformationWrapper(manufacturerField.getText().trim(), colorField.getText().trim(), -1, Integer.parseInt(tireDField.getText().trim()));
        } else {
            return new InformationWrapper(manufacturerField.getText().trim(), colorField.getText().trim(), Integer.parseInt(frameHField.getText().trim()), Integer.parseInt(tireDField.getText().trim()));

        }
    }
}
