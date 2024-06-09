package it.unibo.model.impl;

import java.util.Random;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constants;

public class CakePositionComponent extends AbstractComponent {

     public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double cakeX = rand.nextDouble() * (Constants.PowerUps.CAKE_MAX_X - Constants.PowerUps.CAKE_MIN_X)
                + Constants.PowerUps.CAKE_MIN_X;
        double cakeY;
        switch (rand.nextInt(3)) {
            case 0:
                cakeY = Constants.Floors.FLOOR_1_Y;
                break;
            case 1:
                cakeY = Constants.Floors.FLOOR_2_Y;
                break;
            default:
                cakeY = Constants.Floors.FLOOR_3_Y;
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