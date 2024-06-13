package it.unibo.controller.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;

/**
 * Class to manage a cake power up.
 */
public class CakeController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Entity cake;
    private final ScheduledExecutorService scheduler;
    /**
     * Constructor.
     * @param gamePerformance
     */
    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = createScheduler();
    }
    /**
     * Method to execute a thread.
     * @return
     */
    protected ScheduledExecutorService createScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }
    /**
     * Method to manage the creation.
     */
    public void scheduleCakeCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            generateAndRemoveCake();
        }, 0, 20, TimeUnit.SECONDS);
    }
    /**
     * Method to create and remove a cake.
     */
    private void generateAndRemoveCake() {
        if (this.cake != null) {
            this.gamePerformance.removeEntity(this.cake);
            this.cake = null;
        }
        CakePositionComponent cakePositionComponent = new CakePositionComponent();
        Pair<Double, Double> cakePosition = cakePositionComponent.randomPosition();
        cake = entityFactoryImpl.createCake(cakePosition);
        this.gamePerformance.addEntity(cake);
        scheduleCakeRemoval(cake);
    }
    /**
     * Method to remove a cake.
     * @param cakeToRemove
     */
    private void scheduleCakeRemoval(Entity cakeToRemove) {
        scheduler.schedule(() -> {
            this.gamePerformance.removeEntity(cakeToRemove);
            if (this.cake == cakeToRemove) {
                this.cake = null;
            }
        }, 10, TimeUnit.SECONDS);
    }
    /**
     * Getter of the cake.
     * @return
     */
    public Entity getCake() {
        return this.cake;
    }
    /**
     * Stop the creation.
     */
    public void stopCakeCreation() {
        scheduler.shutdown();
    }
}