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
import it.unibo.model.api.Entity;

public class BirdView implements View {

    private static final int FRAME_COUNT = 4;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image birdteLeft;
    private Image birdRight;
    private Image sprite;
    private Entity bird;
    private Timeline timeline;
    private int currentFrame = 0;

    public BirdView(final Entity bird) {
        this.bird = bird;
        this.imageView = new ImageView();
        this.birdRight = getSource("BirdMovementRight.png");
        this.birdteLeft = getSource("BirdMovementLeft.png");
    }

    public void animateBird() {
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
    public Image getSource(String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    private Image getImage() {
        Pair<Double, Double> bird = this.bird.getPosition();
        return bird.getX() == Constants.PowerUps.BIRD_MIN_x ? this.birdRight : this.birdteLeft;
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
}