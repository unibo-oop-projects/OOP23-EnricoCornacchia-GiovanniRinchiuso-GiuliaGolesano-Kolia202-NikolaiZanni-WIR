package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BirdController {
    private final EntityFactoryImpl entityFactoryImpl;
    private Entity bird;
    private List<Entity> birds;
    private final GamePerformance gamePerformance;
    private final ScheduledExecutorService scheduler;
    BirdPositionComponent birdPositionComponent = new BirdPositionComponent();

    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.birds = new ArrayList<>();
    }

    public void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            bird = entityFactoryImpl.createBird(null);
            gamePerformance.addEntity(bird);
            birds.add(bird);
        }, Constaints.PowerUps.INITIAL_DELAY, Constaints.PowerUps.PERIOD, TimeUnit.SECONDS);
    }

    public void moveBird() {
        for (final Entity bird : this.birds) {
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
                        this.birds.remove(bird);
                        this.gamePerformance.removeEntity(bird);
                    }
                }
            }
        }
    }

    public List<Entity> getBirds() {
        return this.birds;
    }

    public void stopBirdCreation() {
        scheduler.shutdown();
    }

    public void update() {
        scheduleBirdCreation();
        moveBird();
    }
}
