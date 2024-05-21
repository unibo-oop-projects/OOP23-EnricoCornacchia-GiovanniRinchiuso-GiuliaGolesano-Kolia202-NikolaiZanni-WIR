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

import it.unibo.utilities.Constaints;

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
        assertTrue(randomPos.getX() == Constaints.PowerUps.BIRD_MIN_x
                || randomPos.getX() == Constaints.PowerUps.BIRD_MAX_X);
        assertTrue(randomPos.getY() >= Constaints.PowerUps.BIRD_MIN_Y
                && randomPos.getY() <= Constaints.PowerUps.BIRD_MAX_Y);
    }

    @Test
    public void testHasToMoveRight() {
        Pair<Double, Double> randomPos = birdComponent.randomPosition();
        assertNotNull(randomPos);
        if (randomPos.getX() == Constaints.PowerUps.BIRD_MAX_X) {
            assertFalse(birdComponent.hasToMoveRight());
        } else if (randomPos.getX() == Constaints.PowerUps.BIRD_MIN_x) {
            assertTrue(birdComponent.hasToMoveRight());
        }
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.BIRDPOSITION, birdComponent.getComponent());
    }
}
