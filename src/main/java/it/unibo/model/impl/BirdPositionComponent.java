package it.unibo.model.impl;

import java.util.Random;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constaints;

public class BirdPositionComponent extends AbstractComponent {
    private Pair<Double, Double> birdPosition;
    private boolean moveRight;

    public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double birdY = rand.nextDouble() * (Constaints.PowerUps.BIRD_MAX_Y - Constaints.PowerUps.BIRD_MIN_Y)
                + Constaints.PowerUps.BIRD_MIN_Y;
        double birdX;
        switch (rand.nextInt(2)) {
            case 0:
                birdX = Constaints.PowerUps.BIRD_MAX_X;
                break;
            default:
                birdX = Constaints.PowerUps.BIRD_MIN_x;
                break;
        }
        final Pair<Double, Double> randomPos = new Pair<>(birdX, birdY);
        return randomPos;
    }

    public void setBirdPosition(Pair<Double, Double> position) {
        this.birdPosition = randomPosition();
    }

    public Pair<Double, Double> getBirdPosition() {
        return this.birdPosition;
    }

    public boolean rightMove() {
        if (birdPosition.getX() == Constaints.PowerUps.BIRD_MIN_x) {
            return moveRight = true;
        } else {
            return moveRight = false;
        }
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}