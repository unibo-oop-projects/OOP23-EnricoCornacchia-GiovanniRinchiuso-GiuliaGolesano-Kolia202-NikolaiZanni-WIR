package it.unibo.view.impl;

import it.unibo.model.api.Entity;
import it.unibo.view.api.View;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that implement the view of a brick.
 */
public class BrickView implements View {
    private static final int FRAME_WIDTH = 39; 
    private static final int FRAME_HEIGHT = 60; 
    private ImageView imageView;
    private Image spriteSheet;
    private final Entity brick;
    /**
     * Constructor.
     * @param brick
     */
    public BrickView(final Entity brick) {
        spriteSheet = getSource("brick");
        this.brick = brick;
        imageView = new ImageView();
        imageView.setFitHeight(FRAME_HEIGHT);
        imageView.setFitWidth(FRAME_WIDTH);
        this.imageView.setX(brick.getPosition().getX());
        this.imageView.setY(brick.getPosition().getY());  
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
     * @return
     */
    public void animateBrick() {
        this.imageView.setX(this.brick.getPosition().getX());
        this.imageView.setY(this.brick.getPosition().getY());
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
        return this.spriteSheet;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImageView() {
        return this.imageView;
    }
}
