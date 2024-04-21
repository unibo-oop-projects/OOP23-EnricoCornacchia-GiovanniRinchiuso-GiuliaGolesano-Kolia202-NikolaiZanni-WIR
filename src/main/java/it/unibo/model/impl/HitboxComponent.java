package it.unibo.model.impl;

import it.unibo.common.Rectangle;
import it.unibo.model.api.ComponentType;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constaints.Felix;

/**
 * HitboxComponent, it represents the hitbox of the entity.
 */
public class HitboxComponent extends AbstractComponent {

    private Rectangle hitbox;
    private double x;
    private double y;

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
     * Returns the x-coordinate of the entity.
     *
     * @return the x-coordinate of the entity.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x-coordinate of the entity.
     *
     * @param x the new x-coordinate of the entity.
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the entity.
     *
     * @return the y-coordinate of the entity.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the y-coordinate of the entity.
     *
     * @param y the new y-coordinate of the entity.
     */
    public void setY(final double y) {
        this.y = y;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComponent'");
    }
}
