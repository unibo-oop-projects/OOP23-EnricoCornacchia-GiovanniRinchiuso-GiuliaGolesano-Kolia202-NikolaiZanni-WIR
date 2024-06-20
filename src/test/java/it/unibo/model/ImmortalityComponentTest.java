package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.ImmortalityComponent;
import it.unibo.model.impl.LivesComponent;

public class ImmortalityComponentTest {
    ImmortalityComponent immortalityComponent;
    LivesComponent livesComponent;

    @BeforeEach
    void setUp() {
        immortalityComponent = new ImmortalityComponent();
        livesComponent = new LivesComponent();
    }
  
    @Test
    void testSetImmortality() {
        immortalityComponent.setImmortality(livesComponent);
        assertTrue(livesComponent.isImmortality());
    }

    @Test
    void testChekStopImmortality() throws InterruptedException {
        immortalityComponent.setImmortality(livesComponent);
        assertTrue(livesComponent.isImmortality());
        Thread.sleep(10001);
        immortalityComponent.chekStopImmortality(livesComponent);
        assertFalse(livesComponent.isImmortality());
    }
 
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.IMMORTALITY, immortalityComponent.getComponent());
    }
}
