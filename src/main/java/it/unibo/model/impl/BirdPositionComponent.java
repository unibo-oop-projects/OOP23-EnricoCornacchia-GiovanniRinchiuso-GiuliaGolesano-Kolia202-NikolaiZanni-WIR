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

    public Pair<Double, Double> randomPosition() {
        double birdX = Constants.GameEdges.LEFT_WALL;
        double birdY;
        switch (rand.nextInt(3)) {
            case 0:
                birdY = Constants.Floors.FLOOR_1_Y +30;
                break;
            case 1:
                birdY = Constants.Floors.FLOOR_2_Y +30;
                break;
            default:
                birdY = Constants.Floors.FLOOR_3_Y +30;
                break;
        }
        return new Pair<>(birdX, birdY);
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.BIRDPOSITION;
    }
}