package de.kfs.db.controller;

import de.kfs.db.SceneManager;
import de.kfs.db.events.ConfirmEditEvent;
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

    /**
     * Handles the editing of a bike Object with new Data gathered from textfields
     * @param actionEvent
     */
    public void onConfirmEditButtonPressed(ActionEvent actionEvent) {
        if(!numberField.getText().trim().isEmpty()) {

            if((newKeyField.getText().trim().isEmpty() && newBpKeyField.getText().trim().isEmpty())
                    || (!tireDField.getText().trim().isEmpty() || !frameHField.getText().trim().isEmpty()
                    || !manufacturerField.getText().trim().isEmpty() || !colorField.getText().trim().isEmpty())) {
                SceneManager.showWarning("Keine Änderungen eingegebene!");
            } else {
                editAction();

            }
        } else {
            SceneManager.showWarning("Keine Radnummer eingegeben!");
        }


    }
    private void editAction() {


        eventBus.post(new ConfirmEditEvent());
    }
}
