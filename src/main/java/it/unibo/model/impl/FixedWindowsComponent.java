package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;

/**
 * Component of the windows.
 */
public class FixedWindowsComponent implements Component {

    boolean isFixed = false;

    @Override
    public ComponentType getComponent() {
        return ComponentType.FIXEDWINDOWS;
    }
    /**
     * Set the boolean variable.
     */
    public void setFixed(){
        this.isFixed = true;
    }
    /**
     * Getter for the boolean that indicate if a window is fixed or not.
     * @return
     */
    public boolean getFixed(){
        return this.isFixed;
    }
    
}
