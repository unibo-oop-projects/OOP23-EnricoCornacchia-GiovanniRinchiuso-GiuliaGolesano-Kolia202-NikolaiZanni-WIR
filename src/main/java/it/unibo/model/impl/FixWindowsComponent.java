package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
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
    public void fixing(final Pair<Double, Double> windowPosition, final GamePerformance gamePerformance) {
        gamePerformance.getWindows().stream()
        .filter(w -> w.getPosition().equals(windowPosition))
        .findFirst()
        .ifPresent(window -> {
            window.getTheComponent(ComponentType.FIXEDWINDOWS)
                  .map(c -> (FixedWindowsComponent) c)
                  .ifPresent(FixedWindowsComponent::setFixed);
        });
    }
}
