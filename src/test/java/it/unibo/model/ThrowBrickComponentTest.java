package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private ThrowBrickComponent component;
    private Set<Entity> bricks;
    private GamePerformance gamePerformance; 
    private StopRalphComponent stopRalph; 

    @BeforeEach
    void setUp() {
        gamePerformance = new GamePerformanceImpl(null);  // Assume you have an implementation
        component = new ThrowBrickComponent(gamePerformance);
        bricks = new HashSet<>();
        stopRalph = new StopRalphComponent();
    }

    @Test
    void testAddBrickWhenBlocked() {
        System.out.println(System.currentTimeMillis());
        stopRalph.setStopRalph();
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        component.addBrickToThrow(bricks, position);
        System.out.println(System.currentTimeMillis());
        assertEquals(0, bricks.size(), "No brick should be added when component is blocked");
    }

    @Test
    void testAddBrickWhenUnblocked() {
        Pair<Double, Double> position = new Pair<>(1.0, 2.0);
        component.addBrickToThrow(bricks, position);
        assertEquals(1, bricks.size(), "Brick should be added when component is not blocked");
    }
}
