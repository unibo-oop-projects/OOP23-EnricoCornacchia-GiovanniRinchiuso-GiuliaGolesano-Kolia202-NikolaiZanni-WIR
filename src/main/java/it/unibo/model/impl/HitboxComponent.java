package it.unibo.model.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.common.Rectangle;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constants;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constants.Bird;
import it.unibo.utilities.Constants.Brick;
import it.unibo.utilities.Constants.Cake;
import it.unibo.utilities.Constants.Felix;
import it.unibo.utilities.Constants.Ralph;
import it.unibo.utilities.Constants.Window;

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
        if (type == EntityType.FELIX) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
            this.checkPlatformCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                                        Felix.FELIX_WIDTH, Felix.FELIX_HEIGHT);
        } else if (type == EntityType.BRICK) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                                        Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
        } else if (type == EntityType.WINDOW) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                                        Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        } else if (type == EntityType.CAKE) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                                        Cake.CAKE_WIDTH, Cake.CAKE_HEIGHT);
        } else if (type == EntityType.BIRD) {
            this.checkOtherEntitiesCollisions();
            this.hitbox = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                                        Bird.BIRD_WIDTH, Bird.BIRD_HEIGHT);
        }
    }

    /**
     * Checks for collisions with the edges of the game area and handles them accordingly.
     * If the hitbox of the entity goes beyond the left or right walls, it is repositioned to the nearest valid position.
     * If the hitbox of the entity goes beyond the bottom wall and the entity is of type BRICK, it is removed from the game.
     */
    public void checkEdgesCollisions() {
        if (this.hitbox.getX() < Constants.GameEdges.LEFT_WALL) {
            this.hitbox.setX(Constants.GameEdges.LEFT_WALL);
            this.getEntity().setPosition(new Pair<Double, Double>(Constants.GameEdges.LEFT_WALL, this.hitbox.getY()));
        }
        if (this.hitbox.getX() > Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth()) {
            this.hitbox.setX(Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth());
            this.getEntity()
                .setPosition(
                    new Pair<Double, Double>(Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth(), this.hitbox.getY()));
        }
        if (this.hitbox.getY() <= Constants.GameEdges.DOWN_WALL) {
            if (this.getEntity().getEntityType() == EntityType.BRICK) {
                this.getEntity().getGamePerformance().removeBrick(this.getEntity().getPosition());
            }
        }
    }

    /**
     * Checks for collisions with platforms and adjusts the position of the entity accordingly.
     */
    public void checkPlatformCollisions() {
        if (this.hitbox.getY() > Constants.Floors.FLOOR_1_Y && this.hitbox.getY() < Constants.Floors.FLOOR_2_Y) {
            this.hitbox.setY(Constants.Floors.FLOOR_1_Y);
            this.getEntity().setPosition(new Pair<Double, Double>(this.hitbox.getX(), this.hitbox.getY()));
        } else if (this.hitbox.getY() > Constants.Floors.FLOOR_2_Y && this.hitbox.getY() < Constants.Floors.FLOOR_3_Y) {
            this.hitbox.setY(Constants.Floors.FLOOR_2_Y);
            this.getEntity().setPosition(new Pair<Double, Double>(this.hitbox.getX(), this.hitbox.getY()));
        } else if (this.hitbox.getY() > Constants.Floors.FLOOR_3_Y) {
            this.hitbox.setY(Constants.Floors.FLOOR_3_Y);
            this.getEntity().setPosition(new Pair<Double, Double>(this.hitbox.getX(), this.hitbox.getY()));
        }
    }

    /**
     * Checks for collisions with other entities.
     */
    public void checkOtherEntitiesCollisions() {
        for (Entity e : this.getEntity().getGamePerformance().getEntity()) {
            if (!e.equals(this.getEntity())) {
                if (this.collidesWith((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get())) {
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.BRICK) {
                        this.getEntity().getGamePerformance().removeBrick(e.getPosition());
                        ((LivesComponent) this.getEntity().getTheComponent(ComponentType.LIFE).get()).stealLives();
                    }
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.CAKE) {
                        this.getEntity().getGamePerformance().removeEntity(e);
                        ((ImmortalityComponent) this.getEntity().getTheComponent(ComponentType.IMMORTALITY).get())
                                                   .setImmortality(((LivesComponent) this.getEntity()
                                                   .getTheComponent(ComponentType.LIFE).get()));
                    }
                    if (this.getEntity().getEntityType() == EntityType.FELIX && e.getEntityType() == EntityType.BIRD) {
                        this.getEntity().getGamePerformance().removeEntity(e);
                        ((StopRalphComponent) this.getEntity().getTheComponent(ComponentType.STOPRALPH).get())
                                                 .setStopRalph(((ThrowBrickComponent) this.getEntity()
                                                 .getTheComponent(ComponentType.THROWBRICK).get()));
                    }
                }
            }
        }
    }

    /**
     * Checks for collisions with windows in the game and returns the position of the first window collided with.
     * If the entity is Felix and collides with a window, the position of the window is returned.
     * If no collisions occur, an empty Optional is returned.
     * 
     * @return An Optional containing the position of the first window collided with,
     * or an empty Optional if no collisions occurred.
     */
    public Optional<Pair<Double, Double>> checkWindowsCollisions() {
        System.out.println("felix: " + this.getEntity().getPosition() + "punto a destra: " + (this.getEntity().getPosition().getX() + Constants.Felix.FELIX_WIDTH) + (this.getEntity().getPosition().getY()) + "punto in basso: " + (this.getEntity().getPosition().getX()) + (this.getEntity().getPosition().getY() + Constants.Felix.FELIX_HEIGHT));
        for (Entity e : this.getEntity().getGamePerformance().getWindows()) {
            System.out.println("collisione " + e.getPosition() + "punto a destra: " + (e.getPosition().getX() + Constants.Window.WINDOW_WIDTH) + (e.getPosition().getY()) + "punto in basso: " + (e.getPosition().getX()) + (e.getPosition().getY() + Constants.Window.WINDOW_HEIGHT));
            if (this.collidesWith((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get())) {
                System.out.println("collisione INSIDE " + e.getPosition());
                if (this.getEntity().getEntityType().equals(EntityType.FELIX) && e.getEntityType().equals(EntityType.WINDOW)) {
                    System.out.println("posizione finestra: " + e.getPosition());
                    return Optional.of(e.getPosition());
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
