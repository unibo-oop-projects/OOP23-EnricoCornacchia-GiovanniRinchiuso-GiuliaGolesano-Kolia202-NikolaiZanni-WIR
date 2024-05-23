package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GamePerformance;
import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.GamePerformanceImpl;

public class FixWindowsComponentTest {

    FixWindowsComponent fixWindowsComponent;
    FixedWindowsComponent fixedWindowsComponent;

    @BeforeEach
    public void setUp() {
        fixWindowsComponent = new FixWindowsComponent();
        fixedWindowsComponent = new FixedWindowsComponent(false);
    }

    @Test
    public void testFixing() {
        Pair<Double, Double> windowPosition = new Pair<>(0.0, 0.0);
        EntityFactoryImpl entityFactory = new EntityFactoryImpl(new GamePerformanceImpl(new GameController()));
        Entity window = entityFactory.createWindows(windowPosition, false); // Finestra non ancora fissata

        GameController gameController = new GameController();
        GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        gamePerformance.addEntity(window);

        FixWindowsComponent fixWindowsComponent = new FixWindowsComponent();
        fixWindowsComponent.fixing(windowPosition, gamePerformance);

        FixedWindowsComponent fixedWindowsComponent = (FixedWindowsComponent) window.getTheComponent(ComponentType.FIXEDWINDOWS).get();
        assertEquals(true, fixedWindowsComponent.getFixed());
    }


    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.FIXWINDOWS, fixWindowsComponent.getComponent());
    }
}
