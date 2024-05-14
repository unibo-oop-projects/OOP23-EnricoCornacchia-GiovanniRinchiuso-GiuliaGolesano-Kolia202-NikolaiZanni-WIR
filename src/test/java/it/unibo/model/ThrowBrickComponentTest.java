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
import it.unibo.model.impl.ThrowBrickComponent;  

class ThrowBrickComponentTest {
    private ThrowBrickComponent component;
    private Set<Entity> bricks;
    private GamePerformance gamePerformance; 
    private  // Mock or stub if needed

    @BeforeEach
    void setUp() {
        gamePerformance = new GamePerformanceImpl(null);  // Assume you have an implementation
        component = new ThrowBrickComponent(gamePerformance);
        bricks = new HashSet<>();
    }

    @Test
    void testAddBrickWhenUnblocked() {
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        component.addBrickToThrow(bricks, position);
        assertEquals(1, bricks.size(), "Brick should be added when component is not blocked");
    }

    @Test
    void testAddBrickWhenBlocked() {
        component.block();
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        component.addBrickToThrow(bricks, position);
        assertTrue(bricks.isEmpty(), "No brick should be added when component is blocked");
    }

    @Test
    void testBlockAndUnblock() {
        component.block();
        assertTrue(component.isBlocked(), "Component should be blocked");

        component.unblock();
        assertFalse(component.isBlocked(), "Component should be unblocked after calling unblock");
    }
}
