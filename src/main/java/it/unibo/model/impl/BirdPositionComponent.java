package it.unibo.model.impl;

import java.util.Random;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constaints;

public class BirdPositionComponent extends AbstractComponent {

    private boolean moveRight;

    public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double birdY = rand.nextDouble() * (Constaints.PowerUps.BIRD_MAX_Y - Constaints.PowerUps.BIRD_MIN_Y)
                + Constaints.PowerUps.BIRD_MIN_Y;
        double birdX;
        switch (rand.nextInt(2)) {
            case 0:
                birdX = Constaints.PowerUps.BIRD_MAX_X;
                this.moveRight = false;
                break;
            default:
                birdX = Constaints.PowerUps.BIRD_MIN_x;
                this.moveRight = true;
                break;
        }
        final Pair<Double, Double> randomPos = new Pair<>(birdX, birdY);
        return randomPos;
    }

    public boolean hasToMoveRight() {
        return this.moveRight;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}