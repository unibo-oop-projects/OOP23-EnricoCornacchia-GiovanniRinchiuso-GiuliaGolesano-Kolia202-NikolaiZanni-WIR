package it.unibo.view.impl;

import it.unibo.controller.impl.GameController;
import it.unibo.utilities.Constaints;
import javafx.scene.image.Image;

/**
 * Game view, manages the game view.
 */
public class GameView {

    @SuppressWarnings("unused")
    private final GameController gameController;
    @SuppressWarnings("unused")
    private Image[] felix, ralph, brick_fall, window_fix, bird_fly, cake_spawn;

    /**
     * Constructor.
     * 
     * @param gameController
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;
        this.animation();
    }

    /**
     * {@inheritDoc}
     */
    public Image getSource(final String name) {
        return new Image("src/main/resources/" + name + ".png");
    }

    private void animation() {
        this.felix = new Image[Constaints.Animations.NUM_FRAMES_FELIX];
        this.ralph = new Image[Constaints.Animations.NUM_FRAMES_RALPH];
        this.brick_fall = new Image[Constaints.Animations.NUM_FRAMES_BRICK];
        this.bird_fly = new Image[Constaints.Animations.NUM_FRAMES_BIRD];
        this.window_fix = new Image[Constaints.Animations.NUM_FRAMES_WINDOW];
        this.cake_spawn = new Image[Constaints.Animations.NUM_FRAMES_CAKE];
    }
}
