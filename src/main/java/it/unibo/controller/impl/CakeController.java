package it.unibo.controller.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;

public class CakeController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Entity cake;
    private final ScheduledExecutorService scheduler;

    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = createScheduler();
    }

    protected ScheduledExecutorService createScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduleCakeCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            generateAndRemoveCake();
        }, 0, 10, TimeUnit.SECONDS);
    }

    private void generateAndRemoveCake() {
        CakePositionComponent cakePositionComponent = new CakePositionComponent();
        Pair<Double, Double> cakePosition = cakePositionComponent.randomPosition();
        cake = entityFactoryImpl.createCake(cakePosition);
        this.gamePerformance.addEntity(cake);
        scheduleCakeRemoval(cake);
    }

    private void scheduleCakeRemoval(Entity cakeToRemove) {
        scheduler.schedule(() -> {
            this.gamePerformance.removeEntity(cakeToRemove);
            if (this.cake == cakeToRemove) {
                this.cake = null;
            }
        }, 5, TimeUnit.SECONDS);
    }

    public Entity getCake() {
        return this.cake;
    }

    public void stopCakeCreation() {
        scheduler.shutdown();
    }

    public void update() {
        scheduleCakeCreation();
    }
}