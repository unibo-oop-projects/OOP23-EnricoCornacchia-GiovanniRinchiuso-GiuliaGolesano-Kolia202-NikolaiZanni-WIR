package it.unibo.model.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.common.Rectangle;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constaints.Bird;
import it.unibo.utilities.Constaints.Brick;
import it.unibo.utilities.Constaints.Cake;
import it.unibo.utilities.Constaints.Felix;
import it.unibo.utilities.Constaints.Ralph;
import it.unibo.utilities.Constaints.Window;

/**
 * HitboxComponent, it represents the hitbox of the entity.
 */
public class HitboxComponent extends AbstractComponent {

    private Rectangle hitbox;
    @SuppressWarnings("unused")
    private final double x, y;

    /**
     * Constructs a new HitboxComponent with the specified x and y coordinates and entity type.
     * Depending on the entity type, the dimensions of the hitbox are set differently.
     *
     * @param x the x-coordinate of the entity initial position
     * @param y the y-coordinate of the entity initial position
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
                this.hitbox = new Rectangle(x, y, Ralph.RALPH_WIDTH, Ralph.RALPH_HEIGHT);
                break;
            case BRICK:
                this.hitbox = new Rectangle(x, y, Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
                break;
            case WINDOW:
                this.hitbox = new Rectangle(x, y, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
                break;
            case CAKE:
                this.hitbox = new Rectangle(x, y, Cake.CAKE_WIDTH, Cake.CAKE_HEIGHT);
                break;
            case BIRD:
                this.hitbox = new Rectangle(x, y, Bird.BIRD_WIDTH, Bird.BIRD_HEIGHT);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the hitbox of the entity.
     */
    public void update() {
        Entity entity = this.getEntity();
        final EntityType type = entity.getEntityType();
        this.checkEdgesCollisions();
        if (type==EntityType.FELIX) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
            this.checkPlatformCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), Felix.FELIX_WIDTH, Felix.FELIX_HEIGHT);
        } else if (type==EntityType.BRICK) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
        } else if (type==EntityType.WINDOW) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        } else if (type==EntityType.CAKE) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), Cake.CAKE_WIDTH, Cake.CAKE_HEIGHT);
        } else if (type==EntityType.BIRD) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), Bird.BIRD_WIDTH, Bird.BIRD_HEIGHT);
        }
    }

    /**
     * Checks for collisions with the edges of the game area and handles them accordingly.
     * If the hitbox of the entity goes beyond the left or right walls, it is repositioned to the nearest valid position.
     * If the hitbox of the entity goes beyond the bottom wall and the entity is of type BRICK, it is removed from the game.
     */
    public void checkEdgesCollisions() {
        if (this.hitbox.getX() < Constaints.GameEdges.LEFT_WALL) {
            this.hitbox.setX(Constaints.GameEdges.LEFT_WALL);
            this.getEntity().setPosition(new Pair<Double,Double>(Constaints.GameEdges.LEFT_WALL, this.hitbox.getY()));
        }
        if (this.hitbox.getX() > Constaints.GameEdges.RIGHT_WALL - this.hitbox.getWidth()) {
            this.hitbox.setX(Constaints.GameEdges.RIGHT_WALL - this.hitbox.getWidth());
            this.getEntity().setPosition(new Pair<Double,Double>(Constaints.GameEdges.RIGHT_WALL - this.hitbox.getWidth(), this.hitbox.getY()));
        }
        if (this.hitbox.getY() <= Constaints.GameEdges.DOWN_WALL) {
            if (this.getEntity().getEntityType() == EntityType.BRICK) {
                this.getEntity().getGamePerformance().removeBrick(this.getEntity().getPosition());
            }
        }
    }
    
    /**
     * Checks for collisions with platforms and adjusts the position of the entity accordingly.
     */
    public void checkPlatformCollisions() {
        if (this.hitbox.getY() > Constaints.Floors.FLOOR_1_Y && this.hitbox.getY() < Constaints.Floors.FLOOR_2_Y) {
            this.hitbox.setY(Constaints.Floors.FLOOR_1_Y);
            this.getEntity().setPosition(new Pair<Double,Double>(this.hitbox.getX(), this.hitbox.getY()));
        }
        else if (this.hitbox.getY() > Constaints.Floors.FLOOR_2_Y && this.hitbox.getY() < Constaints.Floors.FLOOR_3_Y) {
            this.hitbox.setY(Constaints.Floors.FLOOR_2_Y);
            this.getEntity().setPosition(new Pair<Double,Double>(this.hitbox.getX(), this.hitbox.getY()));
        }
        else if (this.hitbox.getY() > Constaints.Floors.FLOOR_3_Y) {
            this.hitbox.setY(Constaints.Floors.FLOOR_3_Y);
            this.getEntity().setPosition(new Pair<Double,Double>(this.hitbox.getX(), this.hitbox.getY()));
        }
    }

    /**
     * Checks for collisions with other entities.
     */
    public void checkOtherEntitiesCollisions() {
        for (Entity e : this.getEntity().getGamePerformance().getEntity()) {
            if (!e.equals(this.getEntity())) {
                if (this.collidesWith((HitboxComponent)e.getTheComponent(ComponentType.HITBOX).get())) {
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.BRICK) {
                        this.getEntity().getGamePerformance().removeBrick(e.getPosition());
                        //this.getEntity().getGamePerformance().oneLifeLost();
                    }
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.CAKE) {
                        //this.getEntity().getGamePerformance().removeCake(e.getPosition());
                        //this.getEntity().getGamePerformance().oneLifeEarned();
                    }
                }
            }
        }
    }

    public Optional<Pair<Double, Double>> checkWindowsCollisions() {
        for (Entity e : this.getEntity().getGamePerformance().getEntity()) {
            if (!e.equals(this.getEntity())) {
                if (this.collidesWith((HitboxComponent)e.getTheComponent(ComponentType.HITBOX).get())) {
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.WINDOW) {
                        return Optional.of(e.getPosition());
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    public Rectangle getHitbox() {
        return new Rectangle(this.hitbox.getX(), this.hitbox.getY(), this.hitbox.getWidth(), this.hitbox.getHeight());
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
