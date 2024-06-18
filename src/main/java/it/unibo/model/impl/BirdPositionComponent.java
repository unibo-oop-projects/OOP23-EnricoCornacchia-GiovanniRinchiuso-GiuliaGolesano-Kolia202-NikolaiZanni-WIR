package it.unibo.model.impl;

import java.util.Random;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constants;

/**
 * Component to randomly position the bird.
 */
public class BirdPositionComponent extends AbstractComponent {

    private final Random rand = new Random();
    private static final double POS_Y = 30;

    /**
     * Generates a random position for a bird.
     * The bird's X coordinate is fixed at the left wall of the game.
     * The bird's Y coordinate is randomly selected from three possible floors.
     *
     * @return A Pair object representing the bird's position, where the first element 
     * is the X coordinate and the second element is the Y coordinate.
     */
    public Pair<Double, Double> randomPosition() {
        final double birdX = Constants.GameEdges.LEFT_WALL;
        double birdY;
        switch (rand.nextInt(3)) {
            case 0:
                birdY = Constants.Floors.FLOOR_1_Y + POS_Y;
                break;
            case 1:
                birdY = Constants.Floors.FLOOR_2_Y + POS_Y;
                break;
            default:
                birdY = Constants.Floors.FLOOR_3_Y + POS_Y;
                break;
        }
        return new Pair<>(birdX, birdY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}
