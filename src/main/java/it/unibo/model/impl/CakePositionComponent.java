package it.unibo.model.impl;

import java.util.Random;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constants;

/**
 * Class to manage a cake power up position.
 */
public class CakePositionComponent extends AbstractComponent {
    /**
     * Method to return a randomic position.
     * @return
     */
     public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double cakeX = rand.nextDouble() * (Constants.PowerUps.CAKE_MAX_X - Constants.PowerUps.CAKE_MIN_X)
                + Constants.PowerUps.CAKE_MIN_X;
        double cakeY;
        switch (rand.nextInt(3)) {
            case 0:
                cakeY = Constants.Floors.FLOOR_1_Y +30;
                break;
            case 1:
                cakeY = Constants.Floors.FLOOR_2_Y +30;
                break;
            default:
                cakeY = Constants.Floors.FLOOR_3_Y +30;
                break;
        }
        final Pair<Double, Double> randomPos = new Pair<>(cakeX, cakeY);
        return randomPos;
    }
    @Override
    public ComponentType getComponent() {
        return ComponentType.CAKEPOSITION;
    }
}