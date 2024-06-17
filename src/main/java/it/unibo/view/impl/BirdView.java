package it.unibo.view.impl;

import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;

/**
 * Class that implements the view of a bird power up.
 */
public class BirdView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image sprite;
    private Entity bird;
    private Timeline timeline;
    private int currentFrame;

    /**
     * Constructor.
     * @param bird
     */
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
     * Method to create the animation.
     */
    public void animateBird() {
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
        int frameWidth = (int) this.sprite.getWidth() / FRAME_COUNT;
        int frameHeight = (int) this.sprite.getHeight();
        int x = index * frameWidth;
        return new WritableImage(this.sprite.getPixelReader(), x, 0, frameWidth, frameHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImageView() {
        return this.imageView;
    }


    public Entity getBird() {
        return this.bird;
    }
}