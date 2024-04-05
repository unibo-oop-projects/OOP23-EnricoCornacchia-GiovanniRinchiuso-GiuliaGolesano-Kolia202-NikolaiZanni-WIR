package it.unibo.model.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.utilities.EntityType;

/**
 * Entity
 */
public interface Entity {

    /**
     * Returns the set of components associated with this entity.
     *
     * @return the set of components
     */
    Set<Component> getComponents();

    /**
     * Retrieves an optional component of the specified type from this entity.
     *
     * @param componentType the class object representing the type of component to retrieve
     * @return an optional containing the component, or an empty optional if the component is not present
     */
    Optional<Component> getComponent(final Class<? extends Component> componentType);

    /**
     * Add a component to the entity.
     *
     * @param component the component to add
     */
    void addComponent(final Component component);

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
    void setPosition(final Pair<Double, Double> position);

    /**
     * Get the type of an entity.
     * 
     * @return the type of the entity
     */
    EntityType getEntityType();


}