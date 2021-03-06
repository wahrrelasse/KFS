package de.kfs.db.di;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.kfs.db.SceneManager;
import de.kfs.db.SceneManagerFactory;
import de.kfs.db.bikemanagent.BikeManagement;
import javafx.fxml.FXMLLoader;

public class KFSModule extends AbstractModule {

    final EventBus eventBus = new EventBus();
    final BikeManagement bm = new BikeManagement();
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(SceneManager.class, SceneManager.class).
                build(SceneManagerFactory.class));
        bind(EventBus.class).toInstance(eventBus);
        bind(BikeManagement.class).toInstance(bm);
        bind(FXMLLoader.class).toProvider(FXMLLoaderProvider.class);
    }
}
