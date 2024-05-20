package it.unibo.controller.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints;
import it.unibo.common.Pair;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BirdController {
    private final EntityFactoryImpl entityFactoryImpl;
    private Entity bird;
    private final GamePerformance gamePerformance;
    private final ScheduledExecutorService scheduler;
    private boolean moveRight;

    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        moveRight = false;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleBirdCreation();
    }

    private void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            bird = entityFactoryImpl.createBird(null);
        }, Constaints.PowerUps.INITIAL_DELAY, Constaints.PowerUps.PERIOD, TimeUnit.SECONDS);
    }

    public void birdMovent() {
        final MovementComponent moveComp = (MovementComponent) this.bird.getTheComponent(ComponentType.MOVEMENT).get();
        if (moveComp.canMove(1, 0, bird) && moveRight) {
            moveComp.move(1, 0, bird);
        } else if (moveComp.canMove(-1, 0, bird) && !moveRight) {
            moveComp.move(-1, 0, bird);
        } else {
            gamePerformance.removeEntity(bird);
        }
    }

    public void hasToMoveRight() {
        Pair<Double, Double> currentPos = bird.getPosition();
        if (currentPos.getX() == Constaints.PowerUps.BIRD_MIN_x) {
            moveRight = true;
        } else {
            moveRight = false;
        }
    }

    public Entity getBirds() {
        return this.bird;
    }

    public void stopBirdCreation() {
        scheduler.shutdown();
    }

    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}