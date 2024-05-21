package it.unibo.model.impl;

import java.util.Random;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.Constaints;

public class CakePositionComponent extends AbstractComponent {

     public Pair<Double, Double> randomPosition() {
        Random rand = new Random();
        double cakeX = rand.nextDouble() * (Constaints.PowerUps.CAKE_MAX_X - Constaints.PowerUps.CAKE_MIN_X)
                + Constaints.PowerUps.CAKE_MIN_X;
        double cakeY;
        switch (rand.nextInt(3)) {
            case 0:
                cakeY = Constaints.PowerUps.CAKE_FLOOR_1_Y;
                break;
            case 1:
                cakeY = Constaints.PowerUps.CAKE_FLOOR_2_Y;
                break;
            default:
                cakeY = Constaints.PowerUps.CAKE_FLOOR_3_Y;
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