package de.kfs.db.controller;

import de.kfs.db.SceneManager;
import de.kfs.db.events.ConfirmAddEvent;
import de.kfs.db.structure.BikeKey;
import de.kfs.db.structure.EBike;
import de.kfs.db.structure.EngineType;
import de.kfs.db.structure.InformationWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdvancedAddViewPresenter extends AbstractPresenter {

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
    public TextField colorField;


    @FXML
    public void initialize() {
        engineChocie.getItems().addAll(EngineType.BOSCH, EngineType.BAFANG, EngineType.SHIMANO, EngineType.SANTOUR);
        engineChocie.setValue(EngineType.BOSCH);
    }

    /**
     * Handles the addition of an E-Bike to the datastructure
     *
     * @param actionEvent
     */
    public void onAdvanceAddButtonPressed(ActionEvent actionEvent) {

        if(numberField.getText().isEmpty() || frameNumField.getText().isEmpty()) {
            SceneManager.showWarning("Daten nicht korrekt eingegeben \n");
        } else {
            addAction();
        }

    }

    /**
     * HelperMethod, makes it easier to read
     */
    private void addAction() {
        BikeKey bikeKey;
        if (bpKeyField.getText().isEmpty()) {
            bikeKey = new BikeKey(keyNumField.getText().trim(), keyNumField.getText().trim());
        } else {
            bikeKey = new BikeKey(keyNumField.getText().trim(), bpKeyField.getText().trim());
        }
        //setting defaults if necessary
        if(tireDField.getText().isEmpty()) {
            if(frameHField.getText().isEmpty()) {
                frameHField.setText("-1");
            }
            tireDField.setText("-1");
        }
        InformationWrapper info = new InformationWrapper(manufacturerField.getText().trim(), colorField.getText().trim(), Integer.parseInt(frameHField.getText().trim()), Integer.parseInt(tireDField.getText().trim()));

        EBike eb = new EBike(numberField.getText().trim(), bikeKey, frameNumField.getText().trim(), info);
        eb.setEngineType(engineChocie.getValue());


        eb.setBackPedalBreak(backPedalBrake.isSelected());
        bikeManagement.addBike(eb);
        eventBus.post(new ConfirmAddEvent());
    }
}
