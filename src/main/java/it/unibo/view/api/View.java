package it.unibo.view.api;

import it.unibo.common.Pair;
import javafx.scene.canvas.GraphicsContext;

/**
 * Interface that manages a view of the game
 */
public interface View {
    
    /**
     * Method that draw a view.
     * 
     * @param g
     */
    void draw(GraphicsContext g);

    /**
     * Method that calls the GameController and asks to update the positions of the entities.
     */
    void update();

    /**
     * Method that manages an input from keyboard.
     * 
     * @param KeyCode
     */
    void KeyboardInputManage(int KeyCode);

    /**
     * Method that manages an input form mouse.
     * 
     * @param clickPos
     */
    void MouseInputManage(Pair<Integer,Integer> clickPos);

}
