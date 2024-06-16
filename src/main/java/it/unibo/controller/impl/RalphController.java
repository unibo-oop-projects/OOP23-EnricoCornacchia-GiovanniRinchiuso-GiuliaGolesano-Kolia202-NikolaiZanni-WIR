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
import it.unibo.common.Pair;

/**
 * Controller for Ralph.
 */
public class RalphController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final Entity ralph;
    private final GamePerformance gamePerformance;
    private long lastThrowTime;
    private double lastMov;
    private int count = 0;
    /**
     * Constructor for the RalphController.
     * @param gamePerformance the game performance.
     */
    public RalphController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
        ralph = entityFactoryImpl.createRalph(Constants.Ralph.RALPH_START);
        lastThrowTime = System.currentTimeMillis();
    }
    /**
     * Move Ralph.
     */
    public void move() {
        if (count % 10 == 0) {
            lastMov = Math.random() > 0.5 ? 1 : -1;
        }
        count++;
        ((MovementComponent) this.ralph.getTheComponent(ComponentType.MOVEMENT).get()).move(lastMov, 0, ralph);
    }
    /**
     * Throw a brick with the left arm.
     * @param bricks the set of bricks.
     */
    public void throwBrickLeftArm(final Set<Entity> bricks) {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                final double x = Constants.Ralph.RALPH_LEFT_HAND.getX() + ralph.getPosition().getX();
                final double y = ralph.getPosition().getY()+ Constants.Ralph.RALPH_LEFT_HAND.getY();
                final Pair<Double, Double> position = new Pair<>(x, y);
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, position);
                //System.out.println("Brick thrown");
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
                final double x = Constants.Ralph.RALPH_RIGHT_HAND.getX() + ralph.getPosition().getX();
                final double y = ralph.getPosition().getY() + Constants.Ralph.RALPH_RIGHT_HAND.getY();
                final Pair<Double, Double> position = new Pair<>(x, y);
                ((ThrowBrickComponent) c).addBrickToThrow(bricks, position);
                //System.out.println("Brick thrown");
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
     * Getter for the time to wait.
     * @return the time to wait.
     */
    private double getTimeToWait() {
        return Constants.Ralph.THROW_TIME / gamePerformance.getLevel();
    }
    /**
     * Update Ralph position, and make him throwing bricks.
     * @param bricks the set of bricks.
     */
    public void update(final Set<Entity> bricks) {
        this.move();
        if (System.currentTimeMillis() - lastThrowTime >= this.getTimeToWait()) {
            //System.out.println("Throwing bricks");
            this.throwBricks(bricks);
            lastThrowTime = System.currentTimeMillis();
        }
    }
    /**
     * Make Ralph throw bricks.
     * @param bricks the set of bricks.
     */
    private void throwBricks(final Set<Entity> bricks) {
       for (int i = 0; i < gamePerformance.getLevel(); i++) {
           this.throwBrickLeftArm(bricks);
           this.throwBrickRightArm(bricks);
       }
    }
}
