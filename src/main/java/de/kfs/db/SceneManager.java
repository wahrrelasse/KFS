package de.kfs.db;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Class manages currently shown scene/window
 */
public class SceneManager {

    private final Stage primaryStage;
    private final Injector injector;

    @Inject
    public SceneManager(Injector injected, @Assisted Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.injector = injected;
        initMainView();
    }

    private void initMainView() {

    }
    /**
     * Subroutine creating parent panes from FXML files
     * <p>
     * This Method tries to create a parent pane from the FXML file specified by
     * the URL String given to it. If the LOG-Level is set to Debug or higher loading
     * is written to the LOG.
     * If it fails to load the view a RuntimeException is thrown.
     *
     * @param fxmlFile FXML file to load the view from
     * @return view loaded from FXML or null
     * @since 2019-09-03
     */
    private Parent initPresenter(String fxmlFile) {
        Parent rootPane;
        FXMLLoader loader = injector.getInstance(FXMLLoader.class);
        try {
            URL url = getClass().getResource(fxmlFile);

            loader.setLocation(url);
            rootPane = loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Could not load View!" + e.getMessage(), e);
        }
        return rootPane;
    }
}
