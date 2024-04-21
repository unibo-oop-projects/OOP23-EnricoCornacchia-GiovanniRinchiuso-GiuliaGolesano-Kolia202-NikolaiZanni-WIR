package it.unibo.controller.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.utilities.Constaints.Felix;

/**
 * Controller for the Felix character.
 */
public class FelixController {

    private final EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    private Entity felix;

    /**
     * Constructs a new FelixController object.
     * Initializes the felix instance using the provided entityFactoryImpl.
     */
    public FelixController() {
        this.felix = entityFactoryImpl.createFelix(Felix.FELIX_START);
    }
    /**
     * Move the character to the right.
     */
    public void moveRight() {
        MovementComponent moveComp = (MovementComponent) this.felix.getComponent(ComponentType.MOVEMENT).get();
        moveComp.move(1, 0, this.felix);
    }
    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        MovementComponent moveComp = (MovementComponent) this.felix.getComponent(ComponentType.MOVEMENT).get();
        moveComp.move(-1, 0, this.felix);
    }
    /**
     * Move the character down.
     */
    public void moveDown() {
        MovementComponent moveComp = (MovementComponent) this.felix.getComponent(ComponentType.MOVEMENT).get();
        moveComp.move(0, -1, this.felix);
    }
    /**
     * Move the character up.
     */
    public void moveUp() {
        MovementComponent moveComp = (MovementComponent) this.felix.getComponent(ComponentType.MOVEMENT).get();
        moveComp.move(0, 1, this.felix);
    }
    /**
     * Jump.
     */
    public void fix() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fix'");
    }
    /**
     * Check if the character is alive.
     * @return true if Felix have more than 0 lives, false otherwise.
     */
    public boolean isAlive() {
        LivesComponent lives = (LivesComponent) this.felix.getComponent(ComponentType.LIFE).get();
        return lives.getLives() > 0;
    }
}
