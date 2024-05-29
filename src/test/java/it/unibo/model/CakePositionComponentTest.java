package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.utilities.Constants;

public class CakePositionComponentTest {

    CakePositionComponent cakePositionComponent;

    @BeforeEach
    public void setUP() {
        cakePositionComponent = new CakePositionComponent();
    }

    @Test
    public void testRandomPosition() {
        Pair<Double, Double> randomPos = cakePositionComponent.randomPosition();
        assertNotNull(randomPos);
        assertTrue(randomPos.getX() >= Constants.PowerUps.CAKE_MIN_X
                && randomPos.getX() <= Constants.PowerUps.CAKE_MAX_X);
        assertTrue(randomPos.getY() == Constants.PowerUps.CAKE_FLOOR_1_Y
                || randomPos.getY() == Constants.PowerUps.CAKE_FLOOR_2_Y
                || randomPos.getY() == Constants.PowerUps.CAKE_FLOOR_3_Y);
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.CAKEPOSITION, cakePositionComponent.getComponent());
    }
}
