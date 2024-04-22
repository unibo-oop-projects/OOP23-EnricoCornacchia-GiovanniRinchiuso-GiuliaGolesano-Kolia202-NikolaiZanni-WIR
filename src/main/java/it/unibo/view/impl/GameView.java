package it.unibo.view.impl;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;

public class GameView implements View{

    private final GameController gameController;

    /**
     * Constructor.
     * 
     * @param gameController
     */
    public GameView(final GameController gameController){
        this.gameController = gameController;
    }

    /**
     * {@inheritDoc}
     */
    public void KeyboardInputManage(int KeyCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'KeyboardInputManage'");
    }

    /**
     * {@inheritDoc}
     */
    public void MouseInputManage(Pair<Integer, Integer> clickPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MouseInputManage'");
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
    public void update() {
        this.gameController.update();
    }
    
}
