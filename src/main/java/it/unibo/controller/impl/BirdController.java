package it.unibo.controller.impl;

import java.util.Set;

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

    public Set<Entity> getBirds() {
        return this.gamePerformance.getBirds();
    }

    private void createBird() {
        BirdPositionComponent birdPositionComponent = new BirdPositionComponent();
        Pair<Double, Double> position = birdPositionComponent.randomPosition();
        Entity bird = new EntityFactoryImpl(this.gamePerformance).createBird(position);
        this.gamePerformance.addEntity(bird);
    }

    public void moveBird() {
        this.checkDirection();
        for (final Entity bird : this.gamePerformance.getBirds()) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(1.0, 0.0, bird);
                }
            }
        }
    }

    private void checkDirection() {
        for (final Entity bird : this.gamePerformance.getBirds()) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT
                && !((MovementComponent) component).canMove(1.0, 0.0, bird)) {
                    this.gamePerformance.removeEntity(bird);
                }
            }
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCreationTime >= CREATION_INTERVAL) {
            createBird();
            lastCreationTime = currentTime;
        }
        moveBird();
    }
}