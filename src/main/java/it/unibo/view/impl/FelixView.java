package it.unibo.view.impl;

import java.util.List;

import it.unibo.model.api.Entity;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Class responsible for the view of Felix.
 */
public class FelixView implements View{

    private static final int FRAME_COUNT = 4;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image spriteLeft;
    private Image spriteRight;
    private Image sprite;
    private Entity felix;
    private Timeline timeline;
    private int currentFrame = 0;

    public FelixView(final Entity felix) {
        this.felix = felix;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("felix_movement_left.png");
        this.spriteRight = getSource("felix_movement_right.png");
    }

    public void draw(GraphicsContext g) {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) return ;
        this.sprite = getImage();
        this.imageView.setFitWidth(this.sprite.getWidth() / FRAME_COUNT);
        this.imageView.setFitHeight(this.sprite.getHeight());
        this.timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
        this.timeline.setCycleCount(FRAME_COUNT);
        this.timeline.setOnFinished(e -> imageView.setImage(getFrame(FRAME_COUNT - 1)));
        this.timeline.play();
    }

    @Override
    public Image getSource(String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    private Image getImage() {
        List<KeyCode> inputs = this.felix.getGamePerformance().getInputs();
        return inputs.get(inputs.size()-1) == KeyCode.RIGHT ? this.spriteRight : this.spriteLeft;
    }

    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame++;
    }

    @Override
    public Image getFrame(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrame'");
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }
}
