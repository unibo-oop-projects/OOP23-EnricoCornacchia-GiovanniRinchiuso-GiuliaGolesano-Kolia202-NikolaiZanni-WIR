package it.unibo.view.impl;

import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;

/**
 * Class that implements the view of a bird power up.
 */
public final class BirdView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private final ImageView imageView;
    private final Image sprite;
    private final Entity bird;
    private final Timeline timeline;
    private int currentFrame;

    /**
     * Constructor.
     * @param bird
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BirdView(final Entity bird) {
        this.bird = bird;
        this.imageView = new ImageView();
        this.sprite = getSource("birdMovementRight");
        this.imageView.setFitHeight(
                ((HitboxComponent) this.bird.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getHeight());
        this.imageView.setFitWidth(
                ((HitboxComponent) this.bird.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getWidth());
        this.imageView.setX(this.bird.getPosition().getX());
        this.imageView.setY(this.bird.getPosition().getY());
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
        if (this.bird != null) {
            this.imageView.setX(this.bird.getPosition().getX());
            this.imageView.setY(this.bird.getPosition().getY());
            if (this.timeline.getStatus() != Animation.Status.RUNNING) {
                this.timeline.play();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Image getSource(final String name) {
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
    public ImageView getImageView() {
        return this.imageView;
    }


    /**
     * Returns the bird entity.
     *
     * @return the bird entity
     */
    public Entity getBird() {
        return this.bird;
    }
}
