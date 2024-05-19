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

    private final Image imageLeft;
    private final Image imageRight;
    private Entity felix;
    private final double frameWidthLeft;
    private final double frameHeightLeft;
    private final double frameWidthRight;
    private final double frameHeightRight;
    private int currentFrame = 0;

    public FelixView(final Entity felix) {
        this.felix = felix;
        this.imageLeft = getSource("felix_movement_left.png");
        this.imageRight = getSource("felix_movement_right.png");
        this.frameWidthLeft = this.imageLeft.getWidth() / 4;
        this.frameHeightLeft = this.imageLeft.getHeight();
        this.frameWidthRight = this.imageRight.getWidth() / 4;
        this.frameHeightRight = this.imageRight.getHeight();
    }

    public void draw(GraphicsContext g) {
        Image image = getImage();
        double frameWidth = image == imageLeft ? frameWidthLeft : frameWidthRight;
        double frameHeight = image == imageLeft ? frameHeightLeft : frameHeightRight;
        //based on the movement direction or state, the frame is selected
        //currentFrame = (currentFrame + 1) % 4; 
        //^this picks the next frame in sequence and loops back to the first frame after the last frame
        double upperCorner = currentFrame * frameWidth;
        g.drawImage(image, upperCorner, 0, frameWidth, frameHeight,
                    felix.getPosition().getX(), felix.getPosition().getY(),
                    Constaints.Felix.FELIX_WIDTH, Constaints.Felix.FELIX_HEIGHT);
    }

    @Override
    public Image getSource(String name) {
        return new Image(name);
    }

    private Image getImage() {
        return this.imageLeft;
        //return felix.getDirection() == Movements.LEFT ? imageLeft : imageRight;
    }

    @Override
    public void updateFrame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFrame'");
    }

    @Override
    public Image getFrame(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrame'");
    }

    @Override
    public ImageView getImageView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    }
}
