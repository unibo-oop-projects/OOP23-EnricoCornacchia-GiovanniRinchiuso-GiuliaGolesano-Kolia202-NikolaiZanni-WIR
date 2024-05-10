package it.unibo.view.api;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        /**
     * Method that look for the image in the classhpath and return it.
     * @param name
     * @return
     */
    Image getSource(final String name);
}
