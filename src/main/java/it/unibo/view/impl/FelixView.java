package it.unibo.view.impl;

import java.util.List;

import it.unibo.model.api.Entity;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Class responsible for the view of Felix.
 */
public class FelixView implements View {

    private static final int FRAME_COUNT = 4;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image spriteLeft;
    private Image spriteRight;
    private Image sprite;
    private Entity felix;
    private Timeline timeline;
    private int currentFrame = 0;

    /**
     * Builder for the Felix view.
     * 
     * @param felix the Felix entity.
     */
    public FelixView(final Entity felix) {
        this.felix = felix;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("felix_movement_left.png");
        this.spriteRight = getSource("felix_movement_right.png");
    }

    /**
     * Animates the Felix sprite.
     * If the animation is already running, the method returns without doing anything.
     */
    public void animateFelix() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) return ;
        this.currentFrame = 0;
        this.sprite = getImage();
        this.imageView.setFitWidth(this.sprite.getWidth() / FRAME_COUNT);
        this.imageView.setFitHeight(this.sprite.getHeight());
        this.timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
        this.timeline.setCycleCount(FRAME_COUNT);
        this.timeline.setOnFinished(e -> imageView.setImage(getFrame(FRAME_COUNT - 1)));
        this.timeline.play();
    }

    @Override
    public Image getSource(final String name) {
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
                                 index * ((int) this.sprite.getWidth()) / FRAME_COUNT, 0, 
                                 ((int) this.sprite.getWidth()) / FRAME_COUNT, (int) this.sprite.getHeight());
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Returns the appropriate sprite based on the last input received.
     *
     * @return The sprite to be displayed.
     */
    private Image getImage() {
        List<KeyCode> inputs = this.felix.getGamePerformance().getInputs();
        return inputs.get(inputs.size() - 1) == KeyCode.RIGHT ? this.spriteRight : this.spriteLeft;
    }
}
