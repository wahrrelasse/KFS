package de.kfs.db;


import com.google.inject.Guice;
import com.google.inject.Injector;
import de.kfs.db.di.KFSModule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FXApp extends Application {

    private BorderPane tes = new BorderPane();

    @Override
    public void start(Stage stage) {

        Injector injector = Guice.createInjector(new KFSModule());


        SceneManagerFactory sceneManagerFactory = injector.getInstance(SceneManagerFactory.class);
        SceneManager sceneManager = sceneManagerFactory.create(stage);

        //showing first Scene
        sceneManager.showOpenScene();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
