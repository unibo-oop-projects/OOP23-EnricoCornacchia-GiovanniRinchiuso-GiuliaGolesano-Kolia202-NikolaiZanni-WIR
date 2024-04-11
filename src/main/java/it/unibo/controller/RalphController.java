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
    EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    Entity ralph;
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
     */
    public void throwBrickLeftArm(final Set<Entity> bricks) {
        for (Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.RALPH_LEFT_HAND);
            }
        }
    }
    /**
     * Throw a brick with the right arm.
     */
    public void throwBrickRightArm(final Set<Entity> bricks) {
        for (Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, Constaints.RALPH_RIGHT_HAND);
            }
        }
    }
}
