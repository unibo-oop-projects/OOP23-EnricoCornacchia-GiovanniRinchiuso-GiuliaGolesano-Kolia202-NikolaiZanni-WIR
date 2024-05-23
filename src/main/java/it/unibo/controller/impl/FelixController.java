package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.MovementComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.utilities.Constaints.Felix;
import java.util.Optional;

/**
 * Controller for the Felix character.
 */
public class FelixController {

    private final EntityFactoryImpl entityFactoryImpl;
    private Entity felix;
    private final GamePerformance gamePerformance;

    /**
     * Constructs a new FelixController object.
     * Initializes the felix instance using the provided entityFactoryImpl.
     * @param gamePerformance the game performance.
     */
    public FelixController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        this.felix = entityFactoryImpl.createFelix(Felix.FELIX_START);
    }
    /**
     * Move the character to the right.
     */
    public void moveRight() {
        final MovementComponent moveComp = (MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get();
        moveComp.move(1, 0, this.felix);
        //System.out.print("moved right\n");
    }
    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        final MovementComponent moveComp = (MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get();
        moveComp.move(-1, 0, this.felix);
        //System.out.print("moved left\n");
    }
    /**
     * Move the character down.
     */
    public void moveDown() {
        final MovementComponent moveComp = (MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get();
        moveComp.move(0, -1, this.felix);
        //System.out.print("moved down\n");
    }
    /**
     * Move the character up.
     */
    public void moveUp() {
        final MovementComponent moveComp = (MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get();
        moveComp.move(0, 1, this.felix);
        //System.out.print("moved up\n");
    }
    /**
     * Fix the window.
     */
    public void fix() {
        //System.out.print("fixing the windows");
    }
    /**
     * Check if the character is alive.
     * @return true if Felix have more than 0 lives, false otherwise.
     */
    public boolean isAlive() {
        final LivesComponent lives = (LivesComponent) this.felix.getTheComponent(ComponentType.LIFE).get();
        return lives.getLives() > 0;
    }
    /**
     * Getter for the Felix entity.
     * @return the Felix entity.
     */
    public Entity getFelix() {
        return this.felix;
    }
    /** 
     * Method to fix the windows.
     */
    public void fixWindow(Pair<Double, Double> windowPosition) {
        FixWindowsComponent fixComp = (FixWindowsComponent) this.felix.getTheComponent(ComponentType.FIXWINDOWS).get();
        fixComp.fixing(windowPosition, this.gamePerformance);
    }
    /**
     * Method to ckeck which window has to be fixed.
     * @return
     */
    public Optional<Pair<Double, Double>> checkWindowsCollisions() {
        HitboxComponent hitboxComponent = (HitboxComponent) this.felix.getTheComponent(ComponentType.HITBOX).orElseThrow(
            () -> new IllegalStateException("Felix does not have a HitboxComponent")
        );
        return hitboxComponent.checkWindowsCollisions();
    }

}
