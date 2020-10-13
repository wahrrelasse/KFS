package de.kfs.db.bikemanagent;

import de.kfs.db.structure.AbstractBike;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BikeManagement {

    private  List<AbstractBike> initialBikes;

    private FilteredList<AbstractBike> flBike;



    public void setInitialBikes(List<AbstractBike> bikes) {
        initialBikes = bikes;
        flBike = new FilteredList<>(FXCollections.observableList(bikes), p-> true);
    }

    public FilteredList<AbstractBike> getFlBike() {
        return flBike;
    }

    /**
     * opens a new FileChooser and loads from that file
     * calls the setInitialBikes Method
     *
     */
    public void loadBikes() {
        final FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());
        if(file != null) {
            setInitialBikes(AbstractBike.load(file));
        } else {
            setInitialBikes(new ArrayList<>());
        }
    }
}
