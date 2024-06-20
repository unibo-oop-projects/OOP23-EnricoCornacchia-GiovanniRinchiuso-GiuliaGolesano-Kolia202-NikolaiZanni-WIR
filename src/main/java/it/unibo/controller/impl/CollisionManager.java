package it.unibo.controller.impl;

import java.util.Iterator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
     * @param gamePerformance the game performance, where every entity is stored.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public CollisionManager(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Getter for the hitboxes.
     * @return the set of hitboxes.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public GamePerformance getGamePerformance() {
        return this.gamePerformance;
    }

    /**
     * Check if there is a collision.
     */
    public void check() {
        final Iterator<Entity> iterator = this.gamePerformance.getEntity().iterator();
        while (iterator.hasNext()) {
            final Entity e = iterator.next();
            final HitboxComponent hitboxComponent = (HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get();
            hitboxComponent.update();
            if (hitboxComponent.shouldRemoveEntity()) {
                iterator.remove();
            }
        }
    }
}
