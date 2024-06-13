package it.unibo.view.impl;

import it.unibo.common.Pair;
import it.unibo.view.api.View;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class to manage a view of a window.
 */
public class WindowsView implements View {
    private static final int FRAME_COUNT = 4;
    private static final int FRAME_WIDTH = 39; 
    private static final int FRAME_HEIGHT = 60; 
    private static final int ANIMATION_DURATION = 1000; 
    private ImageView imageView;
    private Image spriteSheet;
    private Timeline timeline;
    private int currentFrame;
    /**
     * Constructor.
     * @param pos.
     */
    public WindowsView(final Pair<Double, Double> pos) {
        spriteSheet=getSource("window");
        imageView=new ImageView();
        imageView.setFitHeight(FRAME_HEIGHT);
        imageView.setFitWidth(FRAME_WIDTH);
        this.imageView.setX(pos.getX());
        this.imageView.setY(pos.getY());  
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }
    /**
     * Method to change the animation of the fixing window.
     */
    public void fixAnimation() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING){
            return ;
        }
        currentFrame = 0;
        timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
        timeline.setCycleCount(FRAME_COUNT);
        timeline.setOnFinished(e -> imageView.setImage(getFrame(FRAME_COUNT - 1)));
        timeline.play();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame++;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        return new WritableImage(spriteSheet.getPixelReader(), index * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImageView() {
        return this.imageView;
    }
    /**
     * Static view of a fixed window.
     * @return the image view of a fixed window.
     */
    public ImageView fixedwindows() {
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }
    /**
     * Static view of a broken window.
     * @return the image view of a broken window.
     */
    public ImageView brokenWindow() {
        this.imageView.setImage(getFrame(FRAME_COUNT - 1));
        return this.imageView;
    }
}
