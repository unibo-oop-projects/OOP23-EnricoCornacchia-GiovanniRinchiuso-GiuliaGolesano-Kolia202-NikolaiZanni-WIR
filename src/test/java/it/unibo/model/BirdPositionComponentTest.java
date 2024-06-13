package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.BirdPositionComponent;

import it.unibo.utilities.Constants;

public class BirdPositionComponentTest {

    BirdPositionComponent birdComponent;

    @BeforeEach
    public void setUP() {
        birdComponent = new BirdPositionComponent();
    }

    @Test
    public void testRandomPosition() {
        Pair<Double, Double> randomPos = birdComponent.randomPosition();
        assertNotNull(randomPos);
        assertTrue(randomPos.getX() == Constants.PowerUps.BIRD_MIN_X
                || randomPos.getX() == Constants.PowerUps.BIRD_MAX_X);
        assertTrue(randomPos.getY() >= Constants.PowerUps.BIRD_MIN_Y
                && randomPos.getY() <= Constants.PowerUps.BIRD_MAX_Y);
    }

    @Test
    public void testHasToMoveRight() {
        Pair<Double, Double> randomPos = birdComponent.randomPosition();
        assertNotNull(randomPos);
        if (randomPos.getX() == Constants.PowerUps.BIRD_MAX_X) {
            assertFalse(birdComponent.hasToMoveRight());
        } else if (randomPos.getX() == Constants.PowerUps.BIRD_MIN_X) {
            assertTrue(birdComponent.hasToMoveRight());
        }
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.BIRDPOSITION, birdComponent.getComponent());
    }
}
