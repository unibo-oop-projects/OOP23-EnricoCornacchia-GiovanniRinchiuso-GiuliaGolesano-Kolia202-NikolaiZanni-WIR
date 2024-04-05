package it.unibo.model.api;

import it.unibo.common.Pair;

/**
 * Entity
 */
public interface Entity {

    /**
     * Get the position of the entity.
     *
     * @return the position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    Pair<Double, Double> getPosition();

    /**
     * Set the position of the entity.
     *
     * @param pos the new position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    Pair<Double, Double> setPosition(Pair<Double, Double> pos);

    void setMovement(Movement movement);

}