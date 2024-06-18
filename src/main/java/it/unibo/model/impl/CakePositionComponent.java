package it.unibo.model.impl;

import java.util.Random;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constants;

/**
 * Class to manage a cake power up position.
 */
public class CakePositionComponent extends AbstractComponent {
    private static final double POS_Y = 30;

    /**
     * Method to return a randomic position.
     * @return a pair with the x and y position of the cake.
     */
     public Pair<Double, Double> randomPosition() {
        final Random rand = new Random();
        final double cakeX = rand.nextDouble() * (Constants.PowerUps.CAKE_MAX_X - Constants.PowerUps.CAKE_MIN_X)
                + Constants.PowerUps.CAKE_MIN_X;
        double cakeY;
        switch (rand.nextInt(3)) {
            case 0:
                cakeY = Constants.Floors.FLOOR_1_Y + POS_Y;
                break;
            case 1:
                cakeY = Constants.Floors.FLOOR_2_Y + POS_Y;
                break;
            default:
                cakeY = Constants.Floors.FLOOR_3_Y + POS_Y;
                break;
        }
        return new Pair<>(cakeX, cakeY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.CAKEPOSITION;
    }
}
