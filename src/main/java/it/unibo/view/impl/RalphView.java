package it.unibo.view.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.MovementComponent;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class responsible for the view of Ralph.
 */
public class RalphView implements View {

    private static final int FRAME_COUNT_DX = 3;
    private static final int FRAME_COUNT_SX = 2;
    private static final int ANIMATION_DURATION = 1000;
    private final Entity ralph;
    private final ImageView imageView;
    private final Image spriteLeft;
    private final Image spriteRight;
    private Image sprite;
    private Timeline timeline;
    private int currentFrame = 0;

    /**
     * Builder for the Ralph view.
     * 
     * @param ralph the Ralph entity.
     */
    public RalphView(final Entity ralph) {
        this.ralph = ralph;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("ralph_sx");
        this.spriteRight = getSource("ralph_dx");
        this.sprite = this.spriteRight;
        this.imageView.setFitHeight(((HitboxComponent) this.ralph.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getHeight());
        this.imageView.setFitWidth(((HitboxComponent) this.ralph.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getWidth());
        this.imageView.setX(this.ralph.getPosition().getX());
        this.imageView.setY(this.ralph.getPosition().getY());
    }

    void animateRalph() {
        this.imageView.setX(this.ralph.getPosition().getX());
        this.imageView.setY(this.ralph.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.currentFrame = 0;
            this.sprite = getImage();
            this.imageView.setFitWidth(this.sprite.getWidth() / FRAME_COUNT_DX);
            this.imageView.setFitHeight(this.sprite.getHeight());
            this.timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT_DX), e -> updateFrame()));
            this.timeline.setCycleCount(FRAME_COUNT_DX);
            this.timeline.setOnFinished(e -> imageView.setImage(getFrame(0)));
            this.timeline.play();
        }
    }

    @Override
    public Image getSource(String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame++;
    }

    @Override
    public Image getFrame(int index) {
        return new WritableImage(this.sprite.getPixelReader(),
                                 index * ((int) this.sprite.getWidth()) / FRAME_COUNT_DX, 0, 
                                 ((int) this.sprite.getWidth()) / FRAME_COUNT_DX, (int) this.sprite.getHeight());
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Returns the standing Ralph image view.
     *
     * @return The standing Ralph image view.
     */
    public ImageView getStandingRalph() {
        this.sprite = this.spriteRight;
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }
    
    /**
     * @return
     */
    private Image getImage() {
        return ((MovementComponent) this.ralph.getTheComponent(ComponentType.MOVEMENT).get())
                                              .getLastPosition().getX() < this.ralph.getPosition().getX()
                                               ? this.spriteRight : this.spriteLeft;
    }
}
