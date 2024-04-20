package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.Entity;

/**
 * This is an abstract class that implements the Component interface.
 * It provides implementations for the common methods of different types of Components.
 */
public abstract class AbstractComponent implements Component {

    private Entity entity;

    /**
     * Returns the entity associated with this component.
     *
     * @return the entity associated with this component.
     */
    protected Entity getEntity() {
        return this.entity;
    }

    /**
     * Sets the entity associated with this component.
     *
     * @param entity the entity to set.
     */
    protected void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
