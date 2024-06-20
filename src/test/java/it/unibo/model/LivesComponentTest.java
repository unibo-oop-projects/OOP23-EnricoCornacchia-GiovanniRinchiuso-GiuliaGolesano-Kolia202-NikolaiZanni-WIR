package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.LivesComponent;

public class LivesComponentTest {

    LivesComponent livesComponent;

    @BeforeEach
    void setUp() {
        livesComponent = new LivesComponent();
    }

    @Test
    void testResetLives() {
        livesComponent.stealLives();
        assertEquals(2, livesComponent.getLives(), "Lives should decrease by 1 when stealLives is called");
        livesComponent.resetLives();
        assertEquals(3, livesComponent.getLives(), "Lives should be reset to 3");
        assertFalse(livesComponent.isImmortality(), "Immortality should be disabled after resetting lives");
    }

    @Test
    void testStealLives() {
        livesComponent.resetLives();
        livesComponent.stealLives();
        assertEquals(2, livesComponent.getLives(), "Lives should decrease by 1 when stealLives is called");
        livesComponent.setImmortality();
        livesComponent.stealLives();
        assertEquals(2, livesComponent.getLives(), "Lives should not decrease when entity is immortal");
    }

    @Test
    void testGetLives() {
        assertEquals(3, livesComponent.getLives(), "Default lives should be 3");
    }

    @Test
    void testSetImmortality() {
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality(), "Immortality should be enabled");
    }

    @Test
    void testIsImmortality() {
        assertFalse(livesComponent.isImmortality(), "Default immortality should be false");
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality(), "Immortality should be enabled");
    }

    @Test
    void testSetStopImmortality() {
        livesComponent.setImmortality();
        livesComponent.setStopImmortality();
        assertFalse(livesComponent.isImmortality(), "Immortality should be disabled");
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.LIFE, livesComponent.getComponent());
    }
}
