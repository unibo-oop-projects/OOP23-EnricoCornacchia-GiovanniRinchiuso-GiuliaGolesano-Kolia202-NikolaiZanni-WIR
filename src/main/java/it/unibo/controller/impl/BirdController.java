package it.unibo.controller.impl;

import java.util.List;
import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;

/**
 * Class to manage a bird power up.
 */
public class BirdController {
    private static final long CREATION_INTERVAL = 10000;
    private long lastCreationTime = 0;

    private final GamePerformance gamePerformance;

    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    private boolean isBirdPresent() {
        return !this.gamePerformance.getBirds().isEmpty();
    }

    public List<Entity> getBirds() {
        return this.gamePerformance.getBirds();
    }

    private void createBird() {
        BirdPositionComponent birdPositionComponent = new BirdPositionComponent();
        Pair<Double, Double> position = birdPositionComponent.randomPosition();
        Entity bird = new EntityFactoryImpl(this.gamePerformance).createBird(position);
        addBird(bird);
    }

    public void moveBird() {
        for (final Entity bird : this.gamePerformance.getBirds()) {
            bird.getTheComponent(ComponentType.BIRDPOSITION).ifPresent(component -> {
                BirdPositionComponent birdPositionComponent = (BirdPositionComponent) component;
                double X = birdPositionComponent.hasToMoveRight() ? 1.0 : -1.0;
                bird.getTheComponent(ComponentType.MOVEMENT).ifPresent(moveComponent -> {
                    MovementComponent movementComponent = (MovementComponent) moveComponent;
                    if (movementComponent.canMove(X, 0.0, bird)) {
                        movementComponent.move(X, 0.0, bird);
                    } else {
                        this.gamePerformance.removeEntity(bird);
                    }
                });
            });
        }
    }

    public void addBird(final Entity bird) {
        this.gamePerformance.addEntity(bird);
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCreationTime >= CREATION_INTERVAL && !isBirdPresent()) {
            createBird();
            lastCreationTime = currentTime;
        }
        moveBird();
    }
}