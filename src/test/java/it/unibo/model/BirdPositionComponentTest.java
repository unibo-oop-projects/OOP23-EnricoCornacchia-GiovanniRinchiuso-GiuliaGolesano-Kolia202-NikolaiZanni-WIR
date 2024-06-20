package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.utilities.Constants;

public class BirdPositionComponentTest {
    BirdPositionComponent birdPositionComponent;

    @BeforeEach
    void setUp() {
        birdPositionComponent = new BirdPositionComponent();
    }

    @Test
    void testRandomPosition() {
        Pair<Double, Double> position = birdPositionComponent.randomPosition();
        assertEquals(Constants.GameEdges.LEFT_WALL, position.getX());
        double birdY = position.getY();
        assertTrue(birdY == Constants.Bird.FLOOR_1_Y_B ||
                birdY == Constants.Bird.FLOOR_2_Y_B ||
                birdY == Constants.Bird.FLOOR_3_Y_B,
                "Y coordinate should be one of the predefined floor values");
    }

    @Test
    void testGetComponent() {
        assertEquals(ComponentType.BIRDPOSITION, birdPositionComponent.getComponent());
    }

}
