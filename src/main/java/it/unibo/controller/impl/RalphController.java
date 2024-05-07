package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.ThrowBrickComponent;
import it.unibo.utilities.Constaints;
import java.util.Set;

/**
 * Controller for Ralph.
 */
public class RalphController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final Entity ralph;
    private final GamePerformance gamePerformance;
    private final int level;
    /**
     * Constructor for the RalphController.
     * @param level the level of the game.
     * @param gamePerformance the game performance.
     */
    public RalphController(final int level, final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.level = level;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        switch (this.level) {
            case 1:
                ralph = entityFactoryImpl.createRalph(Constaints.Ralph.RALPH_START_LEVEL_1);
                break;
            case 2:
                ralph = entityFactoryImpl.createRalph(Constaints.Ralph.RALPH_START_LEVEL_2);
                break;
            case 3:
                ralph = entityFactoryImpl.createRalph(Constaints.Ralph.RALPH_START_LEVEL_3);
                break;
            default:
                ralph = entityFactoryImpl.createRalph(Constaints.Ralph.RALPH_START_LEVEL_4);
                break;
        }
    }
    /**
     * Move Ralph.
     */
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    /**
     * Throw a brick with the left arm.
     * @param bricks the set of bricks.
     */
    public void throwBrickLeftArm(final Set<Entity> bricks) {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.Ralph.RALPH_LEFT_HAND);
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
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.Ralph.RALPH_RIGHT_HAND);
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
     */
    public void update(final Set<Entity> bricks) {
        this.throwBrickLeftArm(bricks);
        this.throwBrickRightArm(bricks);
        this.move();
    }
}
