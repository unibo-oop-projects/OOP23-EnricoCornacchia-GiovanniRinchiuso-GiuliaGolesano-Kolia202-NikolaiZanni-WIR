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
import it.unibo.utilities.Constants;
import it.unibo.utilities.Movements;
import it.unibo.utilities.Constants.Felix;
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
        ((MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get())
                                       .move(1, 0, this.felix);
        //System.out.print("moved right\n");
    }
    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        ((MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get())
                                       .move(-1, 0, this.felix);
        //System.out.print("moved left\n");
    }
    /**
     * Move the character down.
     */
    public void moveDown() {
        ((MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get())
                                       .move(0, this.getPlatform(Movements.DOWN), this.felix);
        //System.out.print("moved down\n");
    }
    /**
     * Move the character up.
     */
    public void moveUp() {
        ((MovementComponent) this.felix.getTheComponent(ComponentType.MOVEMENT).get())
                                       .move(0, this.getPlatform(Movements.UP), this.felix);
        //System.out.print("moved up\n");
    }
    private double getPlatform(final Movements movement) {
        switch (movement) {
            case UP:
                if (this.felix.getPosition().getY() < Constants.Floors.FLOOR_1_Y) {
                    return 0;
                } else {
                    return -200;
                }
            case DOWN:
                if (this.felix.getPosition().getY() > Constants.Floors.FLOOR_3_Y) {
                    return 0;
                } else {
                    return 200;
                }
            default:
                return 0;
        }
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
     * @param windowPosition the position of the window to fix.
     */
    public void fixWindow(final Pair<Double, Double> windowPosition) {
        FixWindowsComponent fixComp = (FixWindowsComponent) this.felix.getTheComponent(ComponentType.FIXWINDOWS).get();
        fixComp.fixing(windowPosition, this.gamePerformance);
    }
    /**
     * Method to ckeck which window has to be fixed.
     * @return the position of the window to fix.
     */
    public Optional<Pair<Double, Double>> checkWindowsCollisions() {
        HitboxComponent hitboxComponent = (HitboxComponent) this.felix.getTheComponent(ComponentType.HITBOX).orElseThrow(
            () -> new IllegalStateException("Felix does not have a HitboxComponent")
        );
        return hitboxComponent.checkWindowsCollisions();
    }
}
