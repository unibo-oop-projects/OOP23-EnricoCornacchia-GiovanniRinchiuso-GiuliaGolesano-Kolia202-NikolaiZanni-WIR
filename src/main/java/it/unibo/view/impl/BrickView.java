package it.unibo.view.impl;

import it.unibo.model.api.Entity;
import it.unibo.utilities.Constants;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class that implement the view of a brick.
 */
public class BrickView implements View {
    private static final int ANIMATION_DURATION = 16; 
    private ImageView imageView;
    private Image spriteSheet;
    private final Entity brick;
    private Timeline timeline;
    /**
     * Constructor.
     * @param brick
     */
    public BrickView(final Entity brick) {
        spriteSheet = getSource("brick");
        this.brick = brick;
        imageView = new ImageView();
        imageView.setFitHeight(Constants.Brick.BRICK_HEIGHT);
        imageView.setFitWidth(Constants.Brick.BRICK_WIDTH);
        this.imageView.setX(brick.getPosition().getX());
        this.imageView.setY(brick.getPosition().getY());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        Image image = new Image(getClass().getResourceAsStream("/" + name + ".png"));
        if (image.isError()) {
            System.out.println("Error loading image: " + name);
        }
    return image;
    }
    /**
     * Method to animate the brick.
     */
    public void animateBrick() {
        this.imageView.setX(this.brick.getPosition().getX());
        this.imageView.setY(this.brick.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.timeline = new Timeline(
                    new KeyFrame(Duration.millis(ANIMATION_DURATION), e -> {
                    }));
            this.timeline.setCycleCount(1);
            this.timeline.setOnFinished(e -> this.imageView.setImage(this.spriteSheet));
            this.timeline.play();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
        return;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        return new WritableImage(this.spriteSheet.getPixelReader(),
                                 index * ((int) this.spriteSheet.getWidth()), 0, 
                                 ((int) this.spriteSheet.getWidth()), (int) this.spriteSheet.getHeight());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImageView() {
        return this.imageView;
    }
    /**
     * {@inheritDoc}
     */
    public Image getImage() {
        return this.spriteSheet;
    }
    /**
     * Getter for the brick.
     * @return the brick.
     */
    public Entity getBrick() {
        return this.brick;
    }
}
