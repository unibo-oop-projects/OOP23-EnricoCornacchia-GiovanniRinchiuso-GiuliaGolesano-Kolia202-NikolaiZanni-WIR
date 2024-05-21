package it.unibo.view.impl;

import it.unibo.utilities.Constaints;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.BirdPositionComponent;

public class BirdView implements View {

    private final Image imageLeft;
    private final Image imageRight;
    private Entity bird;
    private final double frameWidthL;
    private final double frameHeightL;
    private final double frameWidthR;
    private final double frameHeightR;
    private Image frameImage;
    private int currentFrame = 0;

    private final BirdPositionComponent birdPositionComponent = new BirdPositionComponent();

    public BirdView(final Entity bird) {
        this.bird = bird;
        this.imageLeft = getSource("birdMovementLeft.png");
        this.imageRight = getSource("birdMovementRight.png");
        this.frameWidthL = imageLeft.getWidth() / Constaints.Animations.NUM_FRAMES_BIRD;
        this.frameHeightL = imageLeft.getHeight();
        this.frameWidthR = imageRight.getWidth() / Constaints.Animations.NUM_FRAMES_BIRD;
        this.frameHeightR = imageRight.getHeight();
    }

    public void draw(GraphicsContext g) {
        updateFrame();
        g.drawImage(frameImage,
                bird.getPosition().getX(), bird.getPosition().getY(),
                Constaints.Bird.BIRD_WIDTH, Constaints.Bird.BIRD_HEIGHT);
    }

    @Override
    public Image getSource(String name) {
        return new Image(name);
    }

    private Image getImage() {
        return birdPositionComponent.hasToMoveRight() ? imageRight : imageLeft;
    }

    @Override
    public void updateFrame() {
        currentFrame = (currentFrame + 1) % Constaints.Animations.NUM_FRAMES_BIRD;
        getFrame(currentFrame);
    }

    @Override
    public Image getFrame(int currentFrame) {
        Image image = getImage();
        if (image == imageLeft) {
            double upperCorner = currentFrame * frameWidthL;
            frameImage = new WritableImage(image.getPixelReader(), (int) upperCorner, 0, (int) frameWidthL,
                    (int) frameHeightL);
        } else {
            double upperCorner = currentFrame * frameWidthR;
            frameImage = new WritableImage(image.getPixelReader(), (int) upperCorner, 0, (int) frameWidthR,
                    (int) frameHeightR);
        }
        return frameImage;
    }

    @Override
    public ImageView getImageView() {
        draw(null); 
        ImageView imageView = new ImageView();
        imageView.setImage(frameImage);
        return imageView;
    }
}
