package de.kfs.db.controller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class AbstractPresenter {



    protected EventBus eventBus;

    @Inject
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }
}
