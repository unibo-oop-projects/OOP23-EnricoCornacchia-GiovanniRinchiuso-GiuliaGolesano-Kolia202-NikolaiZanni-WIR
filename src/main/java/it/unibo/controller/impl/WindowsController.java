package it.unibo.controller.impl;

import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.utilities.Constaints;
import it.unibo.common.Map;
import it.unibo.common.Pair;

public class WindowsController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    //private final Map<Pair<Double,Double>, Integer> Windows;
    private boolean broken;
    /**
     * Constructor.
     * @param gamePerformance the game performance.
     */
    public WindowsController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        switch (this.gamePerformance.getLevel()) {
            case 1:
                createLine(Constaints.Windows.FLOOR_1_Y);
                createLine(Constaints.Windows.FLOOR_2_Y);
                createLine(Constaints.Windows.FLOOR_3_Y);
                break;
            case 2:
                createLine(Constaints.Windows.FLOOR_1_Y);
                createLine(Constaints.Windows.FLOOR_2_Y);
                createLine(Constaints.Windows.FLOOR_3_Y);
                createLine(Constaints.Windows.FLOOR_4_Y);
                break;
            case 3:
                createLine(Constaints.Windows.FLOOR_1_Y);
                createLine(Constaints.Windows.FLOOR_2_Y);
                createLine(Constaints.Windows.FLOOR_3_Y);
                createLine(Constaints.Windows.FLOOR_4_Y);
                createLine(Constaints.Windows.FLOOR_5_Y);
                break;
            case 4:
                createLine(Constaints.Windows.FLOOR_1_Y);
                createLine(Constaints.Windows.FLOOR_2_Y);
                createLine(Constaints.Windows.FLOOR_3_Y);
                createLine(Constaints.Windows.FLOOR_4_Y);
                createLine(Constaints.Windows.FLOOR_5_Y);
                createLine(Constaints.Windows.FLOOR_6_Y);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isWon'");
    }
}
