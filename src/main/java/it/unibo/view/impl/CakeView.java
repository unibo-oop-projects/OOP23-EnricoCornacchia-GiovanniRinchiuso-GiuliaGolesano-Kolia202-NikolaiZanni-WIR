package it.unibo.view.impl;

import it.unibo.model.api.Entity;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

public class CakeView implements View{

    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image cakeUpDown;
    private Image sprite;
    private Entity cake;
    private Timeline timeline;
    private int currentFrame = 0;

    public CakeView(final Entity bird) {
        this.cake = cake;
        this.imageView = new ImageView();
        this.cakeUpDown = getSource("cakeUpDown.png");
    }

    public void animateCake() {
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

    private Image getImage() {
        
    }

}