package de.kfs.db;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 * Class manages currently shown scene/window
 */
public class SceneManager {

    private final Stage primaryStage;
    private final Injector injector;

    private String lastTitle;

    private Scene mainScene;
    private Scene currentScene = null;
    private Scene lastScene = null;

    @Inject
    public SceneManager(Injector injected, @Assisted Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.injector = injected;
        initMainView();
    }

    private void initMainView() {
        if(mainScene == null) {
            Parent rootPane = initPresenter(OpenViewPresenter.fxml);
            mainScene = new Scene(rootPane, 800, 600);
        }
        showScene(mainScene, "KFS test");
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

    /**
     * Showing specified Scene, while remembering previous one
     * @param scene New scene to show
     * @param title Title of that new scene
     */
    private void showScene(final Scene scene, final String title) {

        this.lastScene = this.currentScene;
        this.lastTitle = primaryStage.getTitle();
        this.currentScene = scene;

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
