package it.unibo.controller.impl;
import java.util.HashSet;
import java.util.Set;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints.Brick;

/**
 * Controller for the bricks.
 */
public class BrickController {

    private final Set<Entity> bricks;
    private final GamePerformance gamePerformance;

    /**
     * Constructor for the BrickController.
     * @param gamePerformance the game performance.
     */
    public BrickController(final GamePerformance gamePerformance) {
        bricks = new HashSet<>();
        this.gamePerformance = gamePerformance;
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
     * Check if the bricks are still in the game. 
     * If not, remove them by the set of bricks and also by the set of entities in the gamePerformance.
     */
    private void checkBricks() {
        for (final Entity brick : bricks) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT 
                && !((MovementComponent) component).canMove(Brick.BRICK_SPEED, 0.0, brick)) {
                    bricks.remove(brick);
                    gamePerformance.removeEntity(brick);
                }
            }
        }
    }
}
