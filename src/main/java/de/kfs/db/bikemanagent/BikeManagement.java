package de.kfs.db.bikemanagent;

import de.kfs.db.structure.AbstractBike;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    /**
     * opens a new FileChooser and saves the current bikes
     * to that file
     */
    public void saveBikes() {
        final FileChooser fc = new FileChooser();

        File file = fc.showSaveDialog(new Stage());
        if(file != null) AbstractBike.save(file, flBike); else {
            //TODO errorHandling
        }
    }

    /**
     * Method to change the Predicate of the filteredList
     * effectively overrides current prediacte
     * @param p the Predicate to be applied
     */
    public void changePredicate(Predicate p) {
        flBike.setPredicate(p);
    }

    /**
     * Method to add a predicate to already existing predicate
     * (logically AND parameter and current predicate)
     * @param p the predicate to add
     */
    public void addPredicate(Predicate p) {
        flBike.setPredicate(flBike.getPredicate().and(p));
    }
}
