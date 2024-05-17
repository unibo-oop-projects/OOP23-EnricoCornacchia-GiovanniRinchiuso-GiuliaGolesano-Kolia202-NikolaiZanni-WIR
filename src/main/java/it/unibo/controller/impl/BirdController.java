package it.unibo.controller.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints;

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
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduleBirdCreation();
    }

    private void scheduleBirdCreation() {
        scheduler.scheduleAtFixedRate(() -> {
            bird = entityFactoryImpl.createBird(null);
        }, Constaints.PowerUps.INITIAL_DELAY, Constaints.PowerUps.PERIOD, TimeUnit.SECONDS);
    }

    public void moveBird() {
        if (bird != null) {
            if (bird.getPosition().getX() == Constaints.PowerUps.BIRD_MIN_x) {
                final MovementComponent moveComp = (MovementComponent) this.bird.getComponent(ComponentType.MOVEMENT)
                        .get();
                if (bird.getPosition().getX() < Constaints.GameEdges.RIGHT_WALL) {
                    moveComp.move(1, 0, this.bird);
                } else {
                    gamePerformance.removeEntity(bird);
                    bird = null;
                }
            } else if (bird.getPosition().getX() == Constaints.PowerUps.BIRD_MAX_X) {
                final MovementComponent moveComp = (MovementComponent) this.bird.getComponent(ComponentType.MOVEMENT)
                        .get();
                if (bird.getPosition().getX() > Constaints.GameEdges.LEFT_WALL) {
                    moveComp.move(-1, 0, this.bird);
                } else {
                    gamePerformance.removeEntity(bird);
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
}
