package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.model.impl.ThrowBrickComponent;
import it.unibo.utilities.Constants;
import java.util.Set;

/**
 * Controller for Ralph.
 */
public class RalphController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final Entity ralph;
    private final GamePerformance gamePerformance;
    /**
     * Constructor for the RalphController.
     * @param gamePerformance the game performance.
     */
    public RalphController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        ralph = entityFactoryImpl.createRalph(Constants.Ralph.RALPH_START);
    }
    /**
     * Move Ralph.
     */
    public void move() {
        final double random = Math.random() > 0.5 ? 1 : -1;
        final Double nextX = ralph.getPosition().getX() + random;
        ((MovementComponent) this.ralph.getTheComponent(ComponentType.MOVEMENT).get()).move(nextX, 0, ralph);
    }
    /**
     * Throw a brick with the left arm.
     * @param bricks the set of bricks.
     */
    public void throwBrickLeftArm(final Set<Entity> bricks) {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constants.Ralph.RALPH_LEFT_HAND);
            }
        }
    }
    /**
     * Throw a brick with the right arm.
     * @param bricks the set of bricks.
     */
    public void throwBrickRightArm(final Set<Entity> bricks) {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constants.Ralph.RALPH_RIGHT_HAND);
            }
        }
    }
    /**
     * Getter for the ralph entity.
     * @return the ralph entity.
     */
    public Entity getRalph() {
        return ralph;
    }
    /**
     * Update Ralph position, and make him throwing bricks.
     * @param bricks the set of bricks.
     */
    public void update(final Set<Entity> bricks) {
        final int cycle = this.gamePerformance.getLevel();
        for (int i = 0; i < cycle; i++) {
            this.throwBrickLeftArm(bricks);
            this.throwBrickRightArm(bricks);
            this.move();
        }
    }
}
