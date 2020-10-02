package de.kfs.db;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OpenViewPresenter {
    public static final String fxml = "/fxml/openView.fxml";

    @FXML
    private MenuItem openNew;
    @FXML
    private MenuItem openMenu;
    @FXML
    private MenuItem openEMenu;
    @FXML
    private ImageView logoSpace;

    public OpenViewPresenter() {


    }


    public void onOpenNewPressed(ActionEvent actionEvent) {

    }

    public void onOpenBikePressed(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(new Stage());

    }

    public void onOpenEBikesPressed(ActionEvent actionEvent) {
    }
}
