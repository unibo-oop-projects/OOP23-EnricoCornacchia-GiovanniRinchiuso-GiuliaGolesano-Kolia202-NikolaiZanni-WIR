package it.unibo.controller.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;

/**
 * Controller for the collision.
 */
public class CollisionManager {

    private Set<HitboxComponent> hitboxes;

    /**
     * Constructor for the CollisionManager.
     * @param entities the set of entities.
     */
    public CollisionManager(final Set<Entity> entities) {
        this.hitboxes = new HashSet<>();
        this.hitboxes.addAll(entities.stream()
            .flatMap(e -> e.getTheComponent(ComponentType.HITBOX).stream())
            .map(c -> (HitboxComponent) c)
            .collect(Collectors.toSet()));
    }

    /**
     * Getter for the hitboxes.
     * @return the set of hitboxes.
     */
    public Set<HitboxComponent> getHitboxes() {
        return this.hitboxes;
    }

    /**
     * Add a hitbox to the set of hitboxes.
     * @param hitbox the hitbox to add.
     */
    public void addHitbox(final HitboxComponent hitbox) {
        this.hitboxes.add(hitbox);
    }

    /**
     * Check if there is a collision.
     */
    public void check() {
        this.hitboxes.stream().forEach(h -> h.update());
    }

}
