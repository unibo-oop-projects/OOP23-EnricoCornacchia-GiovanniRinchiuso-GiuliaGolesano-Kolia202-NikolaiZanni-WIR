package it.unibo.view.impl;

import it.unibo.controller.impl.GameController;
import it.unibo.utilities.Constaints;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Game view, manages the game view.
 */
public class GameView implements View{

    private final GameController gameController;
    private Image[] felix, ralph, brick_fall, window_fix, bird_fly, cake_spawn;


    /**
     * Constructor.
     * 
     * @param gameController
     */
    public GameView(final GameController gameController){
        this.gameController = gameController;
        this.animation();
    }
    
    /**
     * {@inheritDoc}
     */
    public void draw(GraphicsContext g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    /**
     * {@inheritDoc}
     */
    public Image getSource(final String name) {
        return new Image("src/main/resources/"+name+".png");
    }

    private void animation(){
        this.felix = new Image[Constaints.Animations.NUM_FRAMES_FELIX];
        this.ralph = new Image[Constaints.Animations.NUM_FRAMES_RALPH];
        this.brick_fall = new Image[Constaints.Animations.NUM_FRAMES_BRICK];
        this.bird_fly = new Image[Constaints.Animations.NUM_FRAMES_BIRD];
        this.window_fix = new Image[Constaints.Animations.NUM_FRAMES_WINDOW];
        this.cake_spawn = new Image[Constaints.Animations.NUM_FRAMES_CAKE];
    }
}
