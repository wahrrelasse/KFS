package de.kfs.db;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import de.kfs.db.bikemanagent.BikeManagement;
import de.kfs.db.controller.*;
import de.kfs.db.events.ConfirmAddEvent;
import de.kfs.db.events.ConfirmDeleteEvent;
import de.kfs.db.events.ConfirmEditEvent;
import de.kfs.db.events.main.OpenBikeDatabaseEvent;
import de.kfs.db.events.main.OpenNewTableEvent;
import de.kfs.db.events.management.UpdateBikeEvent;
import de.kfs.db.events.table.AdvancedAddEvent;
import de.kfs.db.events.table.DeleteBikeEvent;
import de.kfs.db.events.table.EditBikeEvent;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

/**
 * Class manages currently shown scene/window
 */
@SuppressWarnings("UnstableApiUsage")
public class SceneManager {

    private final Stage primaryStage;
    private Stage secondaryStage;
    private final Injector injector;
    private final BikeManagement bikeManagement;
    private final EventBus eventBus;


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
    public SceneManager(EventBus eventBus, Injector injected, BikeManagement bm, @Assisted Stage primaryStage) {
        this.eventBus = eventBus;
        eventBus.register(this);
        bikeManagement = bm;
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
     * show an error alert instead of having a whole fxml/Presenter pair
     * Further operation of the application will stop (e.g. IOException while loading)
     * @param s the errorMessage to display
     */
    public static void showSeriousError(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR, s);
        a.setOnCloseRequest(e -> Platform.exit());
        a.show();
    }

    /**
     * shows a warning to user e.g. missing information etc.
     * @param s the message
     */
    public static void showWarning(String s) {
        Alert a = new Alert(Alert.AlertType.WARNING, s);
        a.show();
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

    //SceneControl events using guava eventbus

    @Subscribe
    public void onOpenNewTableEvent(OpenNewTableEvent event) {
        //Empty table
        bikeManagement.setInitialBikes(new ArrayList<>());

        //posting that an update to data is available
        eventBus.post(new UpdateBikeEvent());
        showMainScene();
    }

    @Subscribe
    public void  onOpenBikeDatabaseEvent(OpenBikeDatabaseEvent event) {

        bikeManagement.loadBikes();
        eventBus.post(new UpdateBikeEvent());

        showMainScene();


    }
    @Subscribe
    public void onDeleteBikeEvent(DeleteBikeEvent event) {
        showDeleteScene();
    }
    @Subscribe
    public void onEditBikeEvent(EditBikeEvent event) {
        showEditScene();
    }
    @Subscribe
    public void onAdvancedAddEvent(AdvancedAddEvent event) {
        showAdvancedAddScene();
    }
    @Subscribe
    public void onConfirmAddEvent(ConfirmAddEvent event) {
        showMainScene();
    }
    @Subscribe
    public void onConfirmDeleteEvent(ConfirmDeleteEvent event) {
        showMainScene();
    }
    @Subscribe
    public void onConfirmEditEvent(ConfirmEditEvent event) {
        showMainScene();
    }



}
