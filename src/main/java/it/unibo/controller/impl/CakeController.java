package it.unibo.controller.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;

public class CakeController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Entity cake;
    private final ScheduledExecutorService scheduler;

    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleCakeCreation();
    }

    private void scheduleCakeCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            generateAndRemoveCake();
        }, 5,10, TimeUnit.SECONDS);
    }

    private void generateAndRemoveCake() {
        cake = entityFactoryImpl.createCake(null);
        scheduleCakeRemoval(cake);
    }

    private void scheduleCakeRemoval(Entity cakeToRemove) {
        scheduler.schedule(() -> {
            gamePerformance.removeEntity(cakeToRemove);
        }, 5, TimeUnit.SECONDS);
    }

    public Entity getBird() {
        return this.cake;
    }

    public void stopCakeCreation() {
        scheduler.shutdown();
    }
}