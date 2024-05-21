package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.LivesComponent;

public class LivesComponentTest {

    LivesComponent livesComponent;

    @BeforeEach
    void setUp() {
        GamePerformance gamePerformance = new GamePerformanceImpl(null);
        livesComponent = new LivesComponent(gamePerformance);
    }

    @Test
    public void testStealLives() {
        livesComponent.stealLives();
        assertEquals(2, livesComponent.getLives());
        livesComponent.stealLives();
        assertEquals(1, livesComponent.getLives());
        livesComponent.stealLives();
        assertEquals(0, livesComponent.getLives());
    }

    @Test
    public void testGetLives() {
        assertEquals(3, livesComponent.getLives());
    }

    @Test
    public void testSetImmortality() {
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
    }

    @Test
    public void testIsimmortality() {
        assertFalse(livesComponent.isImmortality());
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
    }

    @Test
    public void testSetStopImmortality() {
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
        livesComponent.setStopImmortality();
        assertFalse(livesComponent.isImmortality());
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.LIFE, livesComponent.getComponent());
    }
}
