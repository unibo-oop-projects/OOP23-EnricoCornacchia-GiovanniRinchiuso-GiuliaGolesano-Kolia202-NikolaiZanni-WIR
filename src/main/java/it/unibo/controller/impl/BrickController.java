package it.unibo.controller.impl;
import java.util.HashSet;
import java.util.Set;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints.Brick;

/**
 * Controller for the bricks.
 */
public class BrickController {

    private final Set<Entity> bricks;

    /**
     * Constructor for the BrickController.
     */
    public BrickController() {
        bricks = new HashSet<>();
    }
    /**
     * Getter for the bricks.
     * @return the set of bricks.
     */
    public Set<Entity> getBricks() {
        return new HashSet<>(this.bricks);
    }
    /**
     * make the bricks fall.
     */
    public void fallBricks() {
        this.checkBricks();
        for (final Entity brick : bricks) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(Brick.BRICK_SPEED, 0.0, brick);
                }
            }
        }
    }
    /**
     * Check if the bricks are still in the game. If not, remove them.
     */
    private void checkBricks() {
        for (final Entity brick : bricks) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT 
                && !((MovementComponent) component).canMove(Brick.BRICK_SPEED, 0.0, brick)) {
                    bricks.remove(brick);
                }
            }
        }
    }


}
