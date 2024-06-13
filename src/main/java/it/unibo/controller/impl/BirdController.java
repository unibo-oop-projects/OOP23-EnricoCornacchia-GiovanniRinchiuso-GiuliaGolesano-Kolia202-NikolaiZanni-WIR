package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class to manage a bird power up.
 */
public class BirdController {
    private final EntityFactoryImpl entityFactoryImpl;
    private Entity bird;
    private final GamePerformance gamePerformance;
    private final ScheduledExecutorService scheduler;

    /**
     * Constructor.
     * @param gamePerformance
     */
    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = createScheduler();
    }
    /**
     * Thread executor method.
     * @return
     */
    protected ScheduledExecutorService createScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }
    /**
     * Method to manage a bird creation.
     */
    public void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            generateAndRemoveBird();
        }, 0, 10, TimeUnit.SECONDS);
    }
    /**
     * Method to remove a bird.
     */
    private void generateAndRemoveBird() {
        if (this.bird != null) {
            this.gamePerformance.removeEntity(this.bird);
            this.bird = null;
        }
        BirdPositionComponent birdPositionComponent = new BirdPositionComponent();
        Pair<Double, Double> birdPosition = birdPositionComponent.randomPosition();
        bird = entityFactoryImpl.createBird(birdPosition);
        this.gamePerformance.addEntity(bird);
        scheduleBirdMovement(bird);
    }
    /**
     * Method to manage the movements.
     * @param bird
     */
    private void scheduleBirdMovement(Entity bird) {
        scheduler.scheduleAtFixedRate(() -> {
            moveBird();
        }, 0, 1, TimeUnit.SECONDS);
    }
    /**
     * Method to move the bird.
     */
    private void moveBird() {
        if (this.bird != null) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.BIRDPOSITION
                        && ((BirdPositionComponent) component).hasToMoveRight()) {
                    if (component.getComponent() == ComponentType.MOVEMENT
                            && ((MovementComponent) component).canMove(1.0, 0, bird)) {
                        ((MovementComponent) component).move(1.0, 0.0, bird);
                    }
                } else if (component.getComponent() == ComponentType.BIRDPOSITION
                        && !((BirdPositionComponent) component).hasToMoveRight()) {
                    if (component.getComponent() == ComponentType.MOVEMENT
                            && ((MovementComponent) component).canMove(-1.0, 0, bird)) {
                        ((MovementComponent) component).move(-1.0, 0.0, bird);
                    } else {
                        this.gamePerformance.removeEntity(bird);
                    }
                }
            }
        }
    }
    /**
     * Getter of the bird.
     * @return the bird
     */
    public Entity getBird() {
        return this.bird;
    }
    /**
     * Method to stop the creation.
     */
    public void stopBirdCreation() {
        scheduler.shutdown();
    }
    /**
     * Method to update the position.
     */
    public void update() {
        moveBird();
    }
}