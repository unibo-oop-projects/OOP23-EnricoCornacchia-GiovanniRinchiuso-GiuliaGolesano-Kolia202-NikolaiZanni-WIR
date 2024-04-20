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
     * getter of the type of the class.
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComponent'");
    }
}
