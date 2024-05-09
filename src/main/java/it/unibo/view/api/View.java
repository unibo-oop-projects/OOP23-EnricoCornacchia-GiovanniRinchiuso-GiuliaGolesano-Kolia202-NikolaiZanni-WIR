package it.unibo.view.api;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface that manages a view of the game
 */
public interface View {
    /**
     * Method that draw a view.
     * 
     * @param g the graphics context
     */
    void draw(GraphicsContext g);
}
