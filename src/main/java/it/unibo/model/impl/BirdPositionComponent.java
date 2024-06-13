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

    /**
     * Generates a random position for a bird within the specified range.
     * The bird's position will have a random Y coordinate between BIRD_MIN_Y and BIRD_MAX_Y,
     * and a fixed X coordinate either at the right wall or the left wall of the game.
     *
     * @return a Pair object representing the random X and Y coordinates of the bird's position
     */
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

    /**
     * Returns whether the bird has to move to the right.
     *
     * @return true if the bird has to move to the right, false otherwise.
     */
    public boolean hasToMoveRight() {
        return this.moveRight;
    }

    /**
     * Returns the component type of this BirdPositionComponent.
     *
     * @return the component type of this BirdPositionComponent
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}
