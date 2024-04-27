package it.unibo.view.impl;

import it.unibo.model.impl.LivesComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * LivesView represents the view for displaying the player's remaining lives.
 * This class can be extended to customize the lives display.
 */
public class LivesView extends StackPane {
    private final LivesComponent livesComponent;
    private final HBox livesContainer;

    /**
     * Constructs a LivesView with the given LivesComponent.
     *
     * @param livesComponent the LivesComponent to use for lives data
     */
    public LivesView(final LivesComponent livesComponent) {
        this.livesComponent = livesComponent;
        this.livesContainer = new HBox();
        this.getChildren().add(livesContainer);
        updateLivesLabel();
    }

    /**
     * Updates the lives label.
     */
    private void updateLivesLabel() {
        int actualLives = livesComponent.getLives();
        livesContainer.getChildren().clear();

        for (int i = 0; i < actualLives; i++) {
            ImageView lifeImage = new ImageView(new Image("life.png"));
            lifeImage.setFitWidth(40);
            lifeImage.setFitHeight(40);
            livesContainer.getChildren().add(lifeImage);
        }

        livesContainer.setSpacing(5);
    }
}
