package it.unibo.model.impl;

import java.util.Random;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constants;

/**
 * Component to randomly position the bird.
 */
public class BirdPositionComponent extends AbstractComponent {
    private boolean moveRight;

    public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double birdY = rand.nextDouble() 
                       * (Constants.PowerUps.BIRD_MAX_Y - Constants.PowerUps.BIRD_MIN_Y)
                       + Constants.PowerUps.BIRD_MIN_Y;
        double birdX;
        if (rand.nextBoolean()) {
            birdX = Constants.GameEdges.RIGHT_WALL;
            this.moveRight = false; 
        } else {
            birdX = Constants.GameEdges.LEFT_WALL;
            this.moveRight = true; 
        }
        return new Pair<>(birdX, birdY);
    }

    public boolean hasToMoveRight() {
        return this.moveRight;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}