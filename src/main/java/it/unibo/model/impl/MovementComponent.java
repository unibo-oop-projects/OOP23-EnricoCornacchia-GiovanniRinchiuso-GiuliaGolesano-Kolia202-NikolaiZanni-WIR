package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
/**
 * Component that allows movement.
 */
public class MovementComponent implements Component {
    /**
     * Move the entity.
     * @param x for the x axis.
     * @param y for the y axis.
     * @param entity the entity to move.
     */
    public void move(final double x, final double y, final Entity entity) {
        double newX = entity.getPosition().getX() + x;
        double newY = entity.getPosition().getY() + y;
        if (canMove(x, y, entity)) {
            entity.setPosition(new Pair<>(newX, newY));
        }
    }


    // al posto di 10 e 0 ci dovrebbero essere delle costanti, che però non sono state ancora definite
    /**
     * Check if the entity can move.
     * @param x for the x axis.
     * @param y for the y axis.
     * @param entity the entity to move.
     * @return true if the entity can move, false otherwise.
     */
    public boolean canMove(final double x, final double y, final Entity entity) {
        double newX = entity.getPosition().getX() + x;
        double newY = entity.getPosition().getY() + y;
        return newX >= 0 && newX <= 10 && newY >= 0 && newY <= 10;
    }
    /**
     * Getter method for the component type.
     * @return the component type.
     */
    public ComponentType getComponent() {
        return ComponentType.MOVEMENT;
    }
}