package de.kfs.db;

import javafx.stage.Stage;

/**
 * Factory for use of injecting SceneManger via Google guice
 */
public interface SceneManagerFactory {


    /**
     *  Creates instance of the SceneManager
     *
     * @param primaryStage Stage used by JavaFX application
     * @return SceneManger for main application
     */
    SceneManager create(Stage primaryStage);
}
