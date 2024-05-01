package it.unibo.model.impl;

import it.unibo.common.Rectangle;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constaints.Felix;

/**
 * HitboxComponent, it represents the hitbox of the entity.
 */
public class HitboxComponent extends AbstractComponent {

    private Rectangle hitbox;
    private final double x;
    private final double y;

    /**
     * Constructs a new HitboxComponent with the specified x and y coordinates and entity type.
     * Depending on the entity type, the dimensions of the hitbox are set differently.
     *
     * @param x the x-coordinate of the entity
     * @param y the y-coordinate of the entity
     * @param type the type of the entity, which determines the dimensions of the hitbox.
     */
    public HitboxComponent(final double x, final double y, final EntityType type) {
        this.x = x;
        this.y = y;
        switch (type) {
            case FELIX:
                this.hitbox = new Rectangle(x, y, Felix.FELIX_WIDTH, Felix.FELIX_HEIGHT);
                break;
            case RALPH:

                break;
            case BRICK:
                break;
            case WINDOW:
                break;
            case POWER_UP:
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        Entity entity = this.getEntity();
        
    }

    /**
     * Returns the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    public Rectangle getHitbox() {
        return new Rectangle(this.x, this.y, this.hitbox.getWidth(), this.hitbox.getHeight());
    }

    /**
     * Sets the hitbox of the entity.
     *
     * @param hitbox the new hitbox of the entity.
     */
    public void setHitbox(final Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Checks if this hitbox collides with another hitbox.
     *
     * @param other the other hitbox to check collision with
     * @return true if this hitbox collides with the other hitbox, false otherwise.
     */
    public boolean collidesWith(final HitboxComponent other) {
        return this.hitbox.intersects(other.getHitbox());
    }

    /**
     * getter of the type of the class.
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.HITBOX;
    }
}
