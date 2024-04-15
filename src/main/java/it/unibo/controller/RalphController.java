package it.unibo.controller;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.ThrowBrickComponent;
import it.unibo.utilities.Constaints;
import java.util.Set;

/**
 * Controller for Ralph.
 */
public class RalphController {
    private final EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    private final Entity ralph;
    /**
     * Constructor for the RalphController.
     */
    public RalphController() {
       ralph = entityFactoryImpl.createRalph(Constaints.RALPH_START);
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
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.RALPH_LEFT_HAND);
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
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.RALPH_RIGHT_HAND);
            }
        }
    }
}
