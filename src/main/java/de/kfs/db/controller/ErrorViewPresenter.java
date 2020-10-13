package de.kfs.db.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ErrorViewPresenter extends AbstractPresenter {


    @FXML
    public Text errorMsg;


    /**
     * confirming error results in closing application (for now)
     *
     */
    public void onOKPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
