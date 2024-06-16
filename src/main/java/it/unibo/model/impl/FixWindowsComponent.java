package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
/**
 * FixWindowsComponent, Felix fixes the windows.
 */
public class FixWindowsComponent extends AbstractComponent {
    /**
     * Constructor empty.
     * @param state
     */
    public FixWindowsComponent() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.FIXWINDOWS;
    }
    /**
     * Method to fix a windows.
     * @param windowPosition.
     * @param gamePerformance.
     */
    public void fixing(final Pair<Double, Double> windowPosition, final GamePerformance gamePerformance ) {
        //System.err.println("fixWindowComponent called");
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
