package de.kfs.db;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import de.kfs.db.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Class manages currently shown scene/window
 */
public class SceneManager {

    private final Stage primaryStage;
    private Stage secondaryStage;
    private final Injector injector;


    private String lastTitle;


    //Scene that may be switched between
    private Scene openScene;
    private Scene mainScene;
    private Scene editScene;
    private Scene deleteScene;
    private Scene advancedAddScene;

    private Scene currentScene = null;
    private Scene lastScene = null;

    @Inject
    public SceneManager(EventBus eventBus, Injector injected, @Assisted Stage primaryStage) {
        eventBus.register(this);
        this.primaryStage = primaryStage;
        this.injector = injected;
        initViews();
    }

    private void initViews() {
        initMainView();
        initOpenView();
        initEditView();
        initDeleteView();
        initAdvancedAddView();
    }

    private void initOpenView() {
        if (openScene == null) {
            Parent rootPane = initPresenter(OpenViewPresenter.fxml);
            openScene = new Scene(rootPane, 600, 400);
        }
    }

    private void initMainView() {
        if (mainScene == null) {
            Parent rootPane = initPresenter(MainViewPresenter.fxml);
            mainScene = new Scene(rootPane, 800, 600);
        }
    }

    private void initEditView() {
        if (editScene == null) {
            Parent rootPane = initPresenter(EditViewPresenter.fxml);
            editScene = new Scene(rootPane, 600, 290);
        }
    }
    private void initDeleteView() {
        if(deleteScene == null) {
            Parent rootPane = initPresenter(DeleteViewPresenter.fxml);
            deleteScene = new Scene(rootPane, 300, 200);
        }
    }
    private void initAdvancedAddView() {
        if(advancedAddScene == null) {
            Parent rootPane = initPresenter(AdvancedAddViewPresenter.fxml);
            advancedAddScene = new Scene(rootPane, 600, 272);
        }
    }

    /**
     * Subroutine creating parent panes from FXML files
     * <p>
     * This Method tries to create a parent pane from the FXML file specified by
     * the URL String given to it.
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
     *
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

    /**
     * Shows specified Scene, while keeping the current one as parent and blocking all
     * inputs that parent
     * @param scene Scene to be shown as child of currently shown scene
     * @param title title of that new Scene
     */
    private void showChildScene(final Scene scene, final String title) {

        secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(title);

        secondaryStage.initOwner(primaryStage);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.show();


    }

    /**
     * Shows the first Scene where files can be loaded
     */
    public void showOpenScene() {
        showScene(openScene, "KFS v0.4");
    }
    /**
     * Shows the main Scene with the TableView and its options
     */
    public void showMainScene() {
        showScene(mainScene, "Alle Räder");
    }

    /**
     * shows the deleteScene as a child of the current Scene
     */
    public void showDeleteScene() {
        showChildScene(deleteScene, "Löschen...");

    }

    /**
     * shows the editScene as a child of the currentScene
     */
    public void showEditScene() {
        showChildScene(editScene, "Bearbeiten...");
    }

    /**
     * shows the AdvancedAddScene as a child of the currentScene
     */
    public void showAdvancedAddScene() {
        showChildScene(advancedAddScene, "E-Bike hinzufügen...");
    }


}
