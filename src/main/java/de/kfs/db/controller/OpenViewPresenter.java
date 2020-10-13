package de.kfs.db.controller;

import de.kfs.db.events.main.OpenBikeDatabaseEvent;
import de.kfs.db.events.main.OpenNewTableEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OpenViewPresenter extends AbstractPresenter {
    public static final String fxml = "/fxml/openView.fxml";

    @FXML
    private MenuItem openNew;
    @FXML
    private MenuItem openMenu;
    @FXML
    private MenuItem openEMenu;


    public OpenViewPresenter() {


    }


    public void onOpenNewPressed(ActionEvent actionEvent) {
        eventBus.post(new OpenNewTableEvent());
    }

    public void onOpenBikePressed(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(new Stage());

        eventBus.post(new OpenBikeDatabaseEvent());

    }

    public void onOpenEBikesPressed(ActionEvent actionEvent) {
    //TODO EventFile & Implentation

    }

    /**
     * should show help manual later
     * @param actionEvent
     */
    public void onHelpPressed(ActionEvent actionEvent) {
    }
}
