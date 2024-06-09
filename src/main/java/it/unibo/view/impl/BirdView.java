package it.unibo.view.impl;

import it.unibo.utilities.Constants;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;

public class BirdView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image spriteLeft;
    private Image spriteRight;
    private Image sprite;
    private Entity bird;
    private Timeline timeline;
    private int currentFrame;

    public BirdView(final Entity bird) {
        this.bird = bird;
        this.imageView = new ImageView();
        this.spriteRight = getSource("birdMovementRight");
        this.spriteLeft = getSource("birdMovementLeft");
        this.sprite = this.spriteRight;
        this.imageView.setFitHeight(
                ((HitboxComponent) this.bird.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getHeight());
        this.imageView.setFitWidth(
                ((HitboxComponent) this.bird.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getWidth());
        this.imageView.setX(this.bird.getPosition().getX());
        this.imageView.setY(this.bird.getPosition().getY());
        animateBird();
    }

    public void animateBird() {
        this.imageView.setX(this.bird.getPosition().getX());
        this.imageView.setY(this.bird.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.currentFrame = 0;
            this.sprite = getImage();
            this.timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(ANIMATION_DURATION / FRAME_COUNT),
                            e -> updateFrame()));
            this.timeline.setCycleCount(Timeline.INDEFINITE);
            this.timeline.play();
        }
    }

    @Override
    public Image getSource(String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    @Override
    public void updateFrame() {
        currentFrame = currentFrame % FRAME_COUNT; // Ensure frame index is within bounds
        imageView.setImage(getFrame(currentFrame));
        currentFrame++;
    }

    @Override
    public Image getFrame(final int index) {
        int frameWidth = (int) this.sprite.getWidth() / FRAME_COUNT;
        return new WritableImage(this.sprite.getPixelReader(),
                index * frameWidth, 0,
                frameWidth, (int) this.sprite.getHeight());
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    public ImageView getStandingBird() {
        this.sprite = this.spriteRight;
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }

    private Image getImage() {
        Pair<Double, Double> bird = this.bird.getPosition();
        return bird.getX() == Constants.PowerUps.BIRD_MIN_x ? this.spriteRight : this.spriteLeft;
    }
}
