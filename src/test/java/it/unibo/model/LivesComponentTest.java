package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.LivesComponent;
/**
 * Test for the Lives Component.
 */
public class LivesComponentTest {

    LivesComponent livesComponent;
    /**
     * Set the variable.
     */
    @BeforeEach
    public final void setUp() {
        livesComponent = new LivesComponent();
    }
    /**
     * Test for the method stealLives.
     */
    @Test
    public void testStealLives() {
        livesComponent.stealLives();
        assertEquals(2, livesComponent.getLives());
        livesComponent.stealLives();
        assertEquals(1, livesComponent.getLives());
        livesComponent.stealLives();
        assertEquals(0, livesComponent.getLives());
    }
    /**
     * Test for the method that get the lives.
     */
    @Test
    public void testGetLives() {
        assertEquals(3, livesComponent.getLives());
    }
    /**
     * Test for the method that set the immortality.
     */
    @Test
    public void testSetImmortality() {
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
    }
    /**
     * Test for the method that look if the player is immortal or not.
     */
    @Test
    public void testIsimmortality() {
        assertFalse(livesComponent.isImmortality());
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
    }
    /**
     * Test for the method that stop the immortality.
     */
    @Test
    public void testSetStopImmortality() {
        livesComponent.setImmortality();
        assertTrue(livesComponent.isImmortality());
        livesComponent.setStopImmortality();
        assertFalse(livesComponent.isImmortality());
    }
    /**
     * Test for the method that get the right component.
     */
    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.LIFE, livesComponent.getComponent());
    }
}
