package it.unibo.view.impl;

import it.unibo.model.api.Entity;
import it.unibo.utilities.Constaints;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class responsible for the view of Felix.
 */
public class FelixView implements View{

    private static final int FRAME_COUNT = 4;
    private static final int ANIMATION_DURATION = 1000;
    private ImageView imageView;
    private Image spriteLeft;
    private Image spriteRight;
    private Entity felix;
    private final double frameWidthLeft;
    private final double frameHeightLeft;
    private final double frameWidthRight;
    private final double frameHeightRight;
    private int currentFrame = 0;

    public FelixView(final Entity felix) {
        this.felix = felix;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("felix_movement_left.png");
        this.spriteRight = getSource("felix_movement_right.png");
        this.frameWidthLeft = this.spriteLeft.getWidth() / FRAME_COUNT;
        this.frameHeightLeft = this.spriteLeft.getHeight();
        this.frameWidthRight = this.spriteRight.getWidth() / FRAME_COUNT;
        this.frameHeightRight = this.spriteRight.getHeight();
    }

    public void draw(GraphicsContext g) {
        Image sprite = getImage();
        double frameWidth = sprite == spriteLeft ? frameWidthLeft : frameWidthRight;
        double frameHeight = sprite == spriteLeft ? frameHeightLeft : frameHeightRight;
        double upperCorner = currentFrame * frameWidth;
        g.drawImage(sprite, upperCorner, 0, frameWidth, frameHeight,
                    felix.getPosition().getX(), felix.getPosition().getY(),
                    Constaints.Felix.FELIX_WIDTH, Constaints.Felix.FELIX_HEIGHT);
    }

    @Override
    public Image getSource(String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    private Image getImage() {
        return this.spriteLeft;
        //return felix.getDirection() == Movements.LEFT ? spriteLeft : spriteRight;
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
