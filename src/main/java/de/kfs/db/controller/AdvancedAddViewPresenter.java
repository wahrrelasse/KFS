package de.kfs.db.controller;

import de.kfs.db.events.ConfirmAddEvent;
import de.kfs.db.structure.EngineType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdvancedAddViewPresenter extends AbstractPresenter{

    public static String fxml = "/fxml/advancedAddView.fxml";
    @FXML
    public CheckBox backPedalBrake;
    @FXML
    public ChoiceBox<EngineType> engineChocie;
    @FXML
    public TextField numberField;
    @FXML
    public TextField keyNumField;
    @FXML
    public TextField bpKeyField;
    @FXML
    public TextField frameNumField;
    @FXML
    public TextField frameHField;
    @FXML
    public TextField tireDField;
    @FXML
    public TextField manufacturerField;


    @FXML
    public void initialize() {
        engineChocie.getItems().addAll(EngineType.BOSCH, EngineType.BAFANG, EngineType.SHIMANO, EngineType.SANTOUR);
        engineChocie.setValue(EngineType.BOSCH);
    }
    /**
     * Handles the addition of an E-Bike to the datastructure
     * @param actionEvent
     */
    public void onAdvanceAddButtonPressed(ActionEvent actionEvent) {


        eventBus.post(new ConfirmAddEvent());
    }
}
