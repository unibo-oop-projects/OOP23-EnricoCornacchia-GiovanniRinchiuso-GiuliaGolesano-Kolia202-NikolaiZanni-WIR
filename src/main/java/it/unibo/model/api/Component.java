package it.unibo.model.api;

/**
 * Represents a component in the system.
 * A component is an entity that can be updated.
 */
public interface Component {

    /**
     * Updates the component.
     * This method should be called to update the state of the component.
     */
    void update();
}