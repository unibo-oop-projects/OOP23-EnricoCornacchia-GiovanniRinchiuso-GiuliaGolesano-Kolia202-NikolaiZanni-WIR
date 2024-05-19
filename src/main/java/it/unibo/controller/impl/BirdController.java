package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
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
    private final GamePerformance gamePerformance;
    private final ScheduledExecutorService scheduler;
    private List<Entity> birds = new ArrayList<>();

    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleBirdCreation();
    }

    private void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            bird = entityFactoryImpl.createBird(null);
            birds.add(bird);
        }, Constaints.PowerUps.INITIAL_DELAY, Constaints.PowerUps.PERIOD, TimeUnit.SECONDS);
    }

    public void moveBirdLeft() {
        this.checkBirdLeft();
        for (final Entity bird : this.birds) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(-1.0, 0, bird);
                }
            }
        }
    }

    private void checkBirdLeft() {
        birds.removeIf(bird -> {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT
                        && !((MovementComponent) component).canMove(-1, 0, bird)) {
                    gamePerformance.removeEntity(bird);
                    return true;
                }
            }
            return false;
        });
    }

    public void moveBirdRight() {
        this.checkBirdRight();
        for (final Entity bird : this.birds) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(1.0, 0, bird);
                }
            }
        }
    }

    private void checkBirdRight() {
        birds.removeIf(bird -> {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT
                        && !((MovementComponent) component).canMove(1, 0, bird)) {
                    gamePerformance.removeEntity(bird);
                    return true;
                }
            }
            return false;
        });
    }

    public List<Entity> getBirds() {
        return this.birds;
    }

    public void stopBirdCreation() {
        scheduler.shutdown();
    }

    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}