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

public class BirdController {
    private final EntityFactoryImpl entityFactoryImpl;
    private Entity bird;
    private final GamePerformance gamePerformance;
    private final ScheduledExecutorService scheduler;

    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = createScheduler();
    }

    protected ScheduledExecutorService createScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            generateAndRemoveBird();
        }, 0, 10, TimeUnit.SECONDS);
    }

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

    private void scheduleBirdMovement(Entity bird) {
        scheduler.scheduleAtFixedRate(() -> {
            moveBird();
        }, 0, 1, TimeUnit.SECONDS);
    }

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

    public Entity getBird() {
        return this.bird;
    }

    public void stopBirdCreation() {
        scheduler.shutdown();
    }

    public void update() {
        moveBird();
    }
}