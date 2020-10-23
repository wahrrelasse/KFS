package de.kfs.db.controller;

import de.kfs.db.controller.filter.IntegerFieldFilter;
import de.kfs.db.events.ConfirmDeleteEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteViewPresenter extends AbstractPresenter{

    public static String fxml = "/fxml/deleteView.fxml";
    @FXML
    public TextField deleteNumberField;

    @FXML
    public void initialize() {
        deleteNumberField.setTextFormatter(new IntegerFieldFilter());
    }
    /**
     * deletes the Bike specified by internalNumber out of the textField
     *
     * @param actionEvent
     */
    public void onDeleteButtonPressed(ActionEvent actionEvent) {
        bikeManagement.deleteBike(deleteNumberField.getText().trim());

        eventBus.post(new ConfirmDeleteEvent());
    }
}
