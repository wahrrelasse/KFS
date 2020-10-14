package de.kfs.db.controller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import de.kfs.db.bikemanagent.BikeManagement;

public class AbstractPresenter {


    @Inject
    protected BikeManagement bikeManagement;


    protected EventBus eventBus;

    @Inject
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }
}
