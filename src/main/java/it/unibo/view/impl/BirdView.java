package it.unibo.view.impl;

import it.unibo.utilities.Constaints;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import it.unibo.model.api.Entity;

public class BirdView implements View {

    private final Image imageLeft;
    private final Image imageRight;
    private Entity bird;
    private final double frameWidthLeft;
    private final double frameHeightLeft;
    private final double frameWidthRight;
    private final double frameHeightRight;
    private int currentFrame = 0;

    public BirdView(final Entity bird) {
        this.bird = bird;
        this.imageLeft = getSource("birdMovementLeft.png");
        this.imageRight = getSource("birdMovementRight.png");
        this.frameWidthLeft = this.imageLeft.getWidth() / 4;
        this.frameHeightLeft = this.imageLeft.getHeight();
        this.frameWidthRight = this.imageRight.getWidth() / 4;
        this.frameHeightRight = this.imageRight.getHeight();
    }

    public void draw(GraphicsContext g) {
        Image image = getImage();
        double frameWidth = image == imageLeft ? frameWidthLeft : frameWidthRight;
        double frameHeight = image == imageLeft ? frameHeightLeft : frameHeightRight;
        double upperCorner = currentFrame * frameWidth;
        g.drawImage(image, upperCorner, 0, frameWidth, frameHeight,
                bird.getPosition().getX(), bird.getPosition().getY(),
                Constaints.Bird.BIRD_WIDTH, Constaints.Bird.BIRD_HEIGHT);
    }

    @Override
    public Image getSource(String name) {
        return new Image(name);
    }

    private Image getImage() {
        return this.imageLeft;
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