package it.unibo.controller.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;

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
        switch (this.gamePerformance.getLevel()) {
            case 1: 
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default: 
                break;
        }
    }
    /**
     * Method that create a floor of windows.
     * @param y the y coordinate of the floor.
     */
    private void createLine(final double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLine'");
    }
    /**
     * Method that check if there are broken windows.
     * @return true if the game is won, false otherwise.
     */
    public boolean isWon() {
        for(var w: this.gamePerformance.getWindows()){
            
        }
        return false;
    }
}
