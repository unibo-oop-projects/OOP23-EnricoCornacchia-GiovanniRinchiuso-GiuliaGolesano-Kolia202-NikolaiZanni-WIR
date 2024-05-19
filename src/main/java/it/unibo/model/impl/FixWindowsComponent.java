package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
import javafx.scene.input.KeyCode;

/**
 * FixWindowsComponent, Felix fixes the windows.
 */
public class FixWindowsComponent extends AbstractComponent {
    /**
     * getter of the type of the class.
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.FIXWINDOWS;
    }
    /**
     * Method to fix a windows.
     * @param code
     */
    public void fixing(final KeyCode code/*, coordinate finestra */) {
        //AGGIUNGERE SE SI TROVA IN COLLISIONE CON UNA FINESTRA
        if(code == KeyCode.Z) {
            //ANDARE NELLA FINESTRA GIUSTA E SETTARE IL COMPONENT A TRUE
            //WindowsView.fixAnimation();
        }
    }
}
