package it.unibo.model;

import it.unibo.model.impl.StopRalphComponent;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StopRalphComponentTest {

    private StopRalphComponent stopRalphComponent;

    @BeforeEach
    public void setUp() {
        stopRalphComponent = new StopRalphComponent();
    }

    @Test
    public void testInitiallyNotBlocked() {
        assertFalse(stopRalphComponent.getStopRalph());
    }

    @Test
    public void testSetStopRalph() {
        stopRalphComponent.setStopRalph();
        assertTrue(stopRalphComponent.getStopRalph());
    }

    @Test
    public void testAutoUnblockAfterTimeout() throws InterruptedException {
        stopRalphComponent.setStopRalph();
        assertTrue(stopRalphComponent.getStopRalph());

        // Aspetta un po' più di 10 secondi per testare l'autosblocco
        Thread.sleep(10100);  // Attenzione: l'uso di sleep nei test può rendere i test lenti e meno affidabili

        assertFalse(stopRalphComponent.getStopRalph());
    }

    @Test
    public void testContinuousBlocking() throws InterruptedException {
        stopRalphComponent.setStopRalph();
        assertTrue(stopRalphComponent.getStopRalph());

        Thread.sleep(5000);  // Metà del tempo necessario per sbloccarsi
        assertTrue(stopRalphComponent.getStopRalph());

        Thread.sleep(6000);  // Altri 5 secondi, totale 10 secondi
        assertFalse(stopRalphComponent.getStopRalph());
    }
}
