package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constaints;
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
        return newX >= Constaints.LEFT_WALL 
        && newX <= Constaints.RIGHT_WALL 
        && newY >= Constaints.DOWN_WALL 
        && newY <= Constaints.UP_WALL;
    }
    /**
     * Getter method for the component type.
     * @return the component type.
     */
    public ComponentType getComponent() {
        return ComponentType.MOVEMENT;
    }
}
