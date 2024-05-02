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
    
    public WindowsController(final int level, final GamePerformance gamePerformance){
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        switch (level) {
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

    private void createLine(double y){
        
    }

}
