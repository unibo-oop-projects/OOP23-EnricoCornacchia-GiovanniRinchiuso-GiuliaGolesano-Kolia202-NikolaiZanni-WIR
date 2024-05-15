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
     * @param gamePerformance the game performance, where every entity is stored.
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
        return this.bricks;
    }
    /**
     * make the bricks fall.
     */
    public void fallBricks() {
        this.checkBricks();
        for (final Entity brick : this.bricks) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(0.0, -this.getBrickSpeedByLevel(), brick);
                }
            }
        }
    }
    /**
     * Check if the bricks are still in the game. 
     * If not, remove them by the set of bricks and also by the set of entities in the gamePerformance.
     */
    private void checkBricks() {
        for (final Entity brick : this.bricks) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT 
                && !((MovementComponent) component).canMove(0.0, -this.getBrickSpeedByLevel(), brick)) {
                    this.bricks.remove(brick);
                    this.gamePerformance.removeEntity(brick);
                }
            }
        }
    }

    private double getBrickSpeedByLevel() {
        switch (this.gamePerformance.getLevel()) {
            case 1:
                return Brick.BRICK_SPEED_LEVEL_1;
            case 2:
                return Brick.BRICK_SPEED_LEVEL_2;
            case 3:
                return Brick.BRICK_SPEED_LEVEL_3;
        }
        return 0;
    }
}
