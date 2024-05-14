package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
 
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.StopRalphComponent;
import it.unibo.model.impl.ThrowBrickComponent;  

class ThrowBrickComponentTest {

    private ThrowBrickComponent throwBrickComponent;
    private Set<Entity> bricks;

    @BeforeEach
    void setUp() {
        GamePerformance gamePerformance = new GamePerformanceImpl(null);  // Assume you have an implementation
        throwBrickComponent = new ThrowBrickComponent(gamePerformance);
        bricks = new HashSet<>();
    }

    @Test
    void testAddBrickWhenUnblocked() {
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        throwBrickComponent.addBrickToThrow(bricks, position);
        assertFalse(throwBrickComponent.isBlocked(), "ThrowBrickComponent should not be blocked when adding a brick");
        assertTrue(bricks.size() == 1, "A brick should be added when component is not blocked");
    }

    @Test
    void testAddBrickWhenBlocked() {
        throwBrickComponent.setBlocked();
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        throwBrickComponent.addBrickToThrow(bricks, position);
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked when adding a brick");
        assertTrue(bricks.isEmpty(), "No brick should be added when component is blocked");
    }

    @Test
    void testSetBlocked() {
        throwBrickComponent.setBlocked();
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked after calling setBlocked");
    }

    @Test
    void testSetUnblocked() {
        throwBrickComponent.setBlocked();
        throwBrickComponent.setUnblocked();
        assertFalse(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be unblocked after calling setUnblocked");
    }
}