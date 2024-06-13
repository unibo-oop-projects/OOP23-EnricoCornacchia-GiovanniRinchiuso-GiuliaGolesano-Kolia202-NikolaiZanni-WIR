package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constants;

/**
 * Component that allows movement.
 */
public class MovementComponent extends AbstractComponent {
    /*
     * Constructor empty.
     */
    public MovementComponent() {
    }
    /**
     * Move the entity.
     * 
     * @param x      for the x axis.
     * @param y      for the y axis.
     * @param entity the entity to move.
     */
    public void move(final double x, final double y, final Entity entity) {
        final double newX = entity.getPosition().getX() + x;
        final double newY = entity.getPosition().getY() + y;
        if (canMove(x, y, entity)) {
            entity.setLastPosition(entity.getPosition());
            entity.setPosition(new Pair<>(newX, newY));
        }
    }
    /**
     * Check if the entity can move.
     * 
     * @param x      for the x axis.
     * @param y      for the y axis.
     * @param entity the entity to move.
     * @return true if the entity can move, false otherwise.
     */
    public boolean canMove(final double x, final double y, final Entity entity) {
        final double newX = entity.getPosition().getX() + x;
        final double newY = entity.getPosition().getY() + y;
        return newX >= Constants.GameEdges.LEFT_WALL
                && newX <= Constants.GameEdges.RIGHT_WALL
                && newY >= Constants.GameEdges.DOWN_WALL
                && newY <= Constants.GameEdges.UP_WALL_1;
    }
    /**
     * Getter method for the component type.
     * 
     * @return the component type.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.MOVEMENT;
    }
}