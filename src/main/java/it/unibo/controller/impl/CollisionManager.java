package it.unibo.controller.impl;

import java.util.Iterator;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.HitboxComponent;

/**
 * Controller for the collision.
 */
public class CollisionManager {

    private final GamePerformance gamePerformance;

    /**
     * Constructor for the CollisionManager.
     * @param entities the set of entities.
     */
    public CollisionManager(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Getter for the hitboxes.
     * @return the set of hitboxes.
     */
    public GamePerformance getGamePerformance() {
        return this.gamePerformance;
    }

    /**
     * Check if there is a collision.
     */
    public void check() {
        Iterator<Entity> iterator = this.gamePerformance.getEntity().iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            HitboxComponent hitboxComponent = (HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get();
            hitboxComponent.update();
            if (hitboxComponent.shouldRemoveEntity()) {
                iterator.remove();
            }
        }
    }
}
