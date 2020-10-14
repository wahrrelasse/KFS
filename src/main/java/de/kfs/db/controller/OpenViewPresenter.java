package de.kfs.db.controller;

import de.kfs.db.events.main.OpenBikeDatabaseEvent;
import de.kfs.db.events.main.OpenNewTableEvent;
import javafx.event.ActionEvent;



public class OpenViewPresenter extends AbstractPresenter {
    public static final String fxml = "/fxml/openView.fxml";




    public OpenViewPresenter() {


    }


    public void onOpenNewPressed(ActionEvent actionEvent) {
        eventBus.post(new OpenNewTableEvent());
    }

    public void onOpenBikePressed(ActionEvent actionEvent) {
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
