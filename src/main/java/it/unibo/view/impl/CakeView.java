package it.unibo.view.impl;

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
 * Class that implement the view of a cake power up.
 */
public class CakeView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private Entity cake;
    private ImageView imageView;
    private Image sprite;
    private Timeline timeline;
    private int currentFrame = 0;
    /**
     * Constructor.
     * @param cake
     */
    public CakeView(final Entity cake) {
        this.cake = cake;
        this.imageView = new ImageView();
        this.sprite = getSource("cake");
        this.imageView.setFitHeight(((HitboxComponent) this.cake.getTheComponent(ComponentType.HITBOX).get())
                .getHitbox().getHeight());
        this.imageView.setFitWidth(((HitboxComponent) this.cake.getTheComponent(ComponentType.HITBOX).get())
                .getHitbox().getWidth());
        this.imageView.setX(this.cake.getPosition().getX());
        this.imageView.setY(this.cake.getPosition().getY());
        animeteCake();
    }
    /**
     * Method to create an animation.
     */
    void animeteCake() {
        this.imageView.setX(this.cake.getPosition().getX());
        this.imageView.setY(this.cake.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.currentFrame = 0;
            this.sprite = getImage();
            this.timeline = new Timeline(
                    new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
            this.timeline.setCycleCount(Animation.INDEFINITE);
            this.timeline.play();
        }
    }
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }
    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame = (currentFrame + 1) % FRAME_COUNT;
    }
    @Override
    public Image getFrame(final int index) {
        return new WritableImage(this.sprite.getPixelReader(),
                index * ((int) this.sprite.getWidth()) / FRAME_COUNT, 0,
                ((int) this.sprite.getWidth()) / FRAME_COUNT, (int) this.sprite.getHeight());
    }
    @Override
    public ImageView getImageView() {
        return this.imageView;
    }
    /**
     * Method to get the standing cake.
     * @return the image view.
     */
    public ImageView getStandingCake() {
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }
    /**
     * Method to get the image.
     * @return the image.
     */
    private Image getImage() {
        return this.sprite;
    }
}
