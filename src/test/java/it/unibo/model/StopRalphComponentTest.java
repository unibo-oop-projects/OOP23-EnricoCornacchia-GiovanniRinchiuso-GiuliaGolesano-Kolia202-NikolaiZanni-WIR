package it.unibo.model;

import it.unibo.controller.impl.GameController;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.StopRalphComponent;
import it.unibo.model.impl.ThrowBrickComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StopRalphComponentTest {

    private StopRalphComponent stopRalphComponent;
    private ThrowBrickComponent throwBrickComponent;

    @BeforeEach
    public void setUp() {
        stopRalphComponent = new StopRalphComponent();
        throwBrickComponent = new ThrowBrickComponent(new GamePerformanceImpl(new GameController()));
    }

    @Test
    void testSetStopRalph() {
        stopRalphComponent.setStopRalph(throwBrickComponent);
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked after calling setStopRalph");
    }

    @Test
    void testCheckUnlockRalph() throws InterruptedException {
        stopRalphComponent.setStopRalph(throwBrickComponent);

        // Aspetta un po' più di 10 secondi per testare l'autosblocco
        Thread.sleep(10100);

        stopRalphComponent.checkUnlockRalph(throwBrickComponent);
        assertFalse(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be unblocked after calling checkUnlockRalph");
    }
}
