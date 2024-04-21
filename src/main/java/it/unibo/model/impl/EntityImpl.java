package it.unibo.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.EntityType;


/**
 * EntityImpl.
 */
public class EntityImpl implements Entity {

    private final EntityType type;
    private final Set<Component> components;
    private Pair<Double, Double> position;

    /**
     * EntityImpl constructor.
     *
     * @param type     the type of the entity
     * @param position the position of the entity
     * @param components the components of the entity
     */
    public EntityImpl(final EntityType type, final Pair<Double, Double> position, final Set<Component> components) {
        this.type = type;
        this.components = new HashSet<>();
        this.position = position;
        this.components.addAll(components);
    }
    /**
     * Method to add a component to the entity.
     * @param component the component to add.
     */
    @Override
    public void addComponent(final Component component) {
        this.components.add(component);
    }
    /**
     * Getter method for the components.
     * @return the components of the entity.
     */
    @Override
    public Set<Component> getComponents() {
        return Collections.unmodifiableSet(this.components);
    }
    /**
     * Getter method for the component.
     * @param componentType the type of the component.
     * @return the component of the entity.
     */
    @Override
    public Optional<Component> getComponent(final ComponentType componentType) {
        return this.components.stream().filter(c -> c.getComponent().equals(componentType)).findFirst();
    }
    /**
     * Get the position of the entity.
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }
    /**
     * Setter method for the position.
     * @param position the new position of the entity.
     */
    @Override
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }
    /**
     * Getter method for the type.
     * @return the type of the entity.
     */
    @Override
    public EntityType getEntityType() {
        return this.type;
    }
}
