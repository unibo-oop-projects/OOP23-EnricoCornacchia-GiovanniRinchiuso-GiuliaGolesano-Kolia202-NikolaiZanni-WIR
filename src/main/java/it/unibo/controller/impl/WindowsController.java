package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixedWindowsComponent;

public class WindowsController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    /**
     * Constructor.
     * @param gamePerformance the game performance.
     */
    public WindowsController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
    }
    /**
     * Method that check if there are broken windows.
     * @return true if the game is won, false otherwise.
     */
    public boolean isWon() {
        return this.gamePerformance.getWindows().stream()
            .allMatch(w -> w.getTheComponent(ComponentType.FIXEDWINDOWS)
                            .map(c -> ((FixedWindowsComponent) c).getFixed())
                            .orElse(false));
    }
}
