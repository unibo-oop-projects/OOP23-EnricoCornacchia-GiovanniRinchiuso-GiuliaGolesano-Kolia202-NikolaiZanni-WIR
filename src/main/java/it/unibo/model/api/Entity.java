package it.unibo.model.api;

import java.util.Optional;
import java.util.Set;
import it.unibo.common.Pair;
import it.unibo.utilities.EntityType;
/**
 * Entity.
 */
public interface Entity {
    /**
     * Returns the set of components associated with this entity.
     *
     * @return the set of components
     */
    Set<Component> getComponents();
    /**
     * Returns the component of the specified type associated with this entity.
     *
     * @param componentType the type of the component to retrieve
     * @return the component of the specified type, if present
     */
    Optional<Component> getComponent(ComponentType componentType);
    /**
     * Add a component to the entity.
     *
     * @param component the component to add
     */
    void addComponent(Component component);
    /**
     * Get the position of the entity.
     *
     * @return the position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    Pair<Double, Double> getPosition();
    /**
     * Set the position of the entity.
     *
     * @param position the new position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    void setPosition(Pair<Double, Double> position);
    /**
     * Get the type of an entity.
     * 
     * @return the type of the entity
     */
    EntityType getEntityType();
}
