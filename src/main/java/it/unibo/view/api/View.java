package it.unibo.view.api;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Interface that manages a view of the game.
 */
public interface View {
    /**
     * Method that look for the image in the classhpath and return it.
     * @param name
     * @return the source.
     */
    Image getSource(final String name);
    /**
     * Update the frame.
     * @return the frame updated.
     */
    void updateFrame();
    /**
     * Method to return the frame.
     * @param index
     * @return the image.
     */
    Image getFrame(final int index);
    /**
     * Get the image view.
     * @return the image created.
     */
    ImageView getImageView();
}
