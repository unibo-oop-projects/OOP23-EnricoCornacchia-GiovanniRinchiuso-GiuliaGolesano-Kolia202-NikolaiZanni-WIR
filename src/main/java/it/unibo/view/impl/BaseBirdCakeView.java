package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Abstract class that implements the common functionality for entity views.
 */
public abstract class BaseBirdCakeView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    protected final Entity entity;
    private final ImageView imageView;
    private final Image sprite;
    private final Timeline timeline;
    private int currentFrame;

    /**
     * Constructor.
     * 
     * @param entity     the entity to be represented
     * @param spriteName the name of the sprite image
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BaseBirdCakeView(final Entity entity, final String spriteName) {
        this.entity = entity;
        this.imageView = new ImageView();
        this.sprite = getSource(spriteName);
        this.imageView.setFitHeight(
                ((HitboxComponent) this.entity.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getHeight());
        this.imageView.setFitWidth(
                ((HitboxComponent) this.entity.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getWidth());
        this.imageView.setX(this.entity.getPosition().getX());
        this.imageView.setY(this.entity.getPosition().getY());
        this.timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(ANIMATION_DURATION / FRAME_COUNT),
                        e -> updateFrame()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animate() {
        this.imageView.setX(this.entity.getPosition().getX());
        this.imageView.setY(this.entity.getPosition().getY());
        if (this.timeline.getStatus() != Animation.Status.RUNNING) {
            this.timeline.play();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame = (currentFrame + 1) % FRAME_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        final int frameWidth = (int) this.sprite.getWidth() / FRAME_COUNT;
        final int frameHeight = (int) this.sprite.getHeight();
        final int x = index * frameWidth;
        return new WritableImage(this.sprite.getPixelReader(), x, 0, frameWidth, frameHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Returns the entity.
     *
     * @return the entity
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Returns the sprite image.
     *
     * @return the sprite image
     */
    public Image getSprite() {
        return this.sprite;
    }
}
