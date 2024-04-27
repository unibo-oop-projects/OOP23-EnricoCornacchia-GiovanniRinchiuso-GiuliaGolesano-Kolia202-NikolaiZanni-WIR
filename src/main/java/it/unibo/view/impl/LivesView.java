package it.unibo.view.impl;


import it.unibo.model.impl.LivesComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LivesView extends StackPane {
    private LivesComponent livesComponent;
    private HBox livesContainer;

    public LivesView(LivesComponent livesComponent) {
        this.livesComponent = livesComponent;
        this.livesContainer = new HBox();
        this.getChildren().add(livesContainer);
        updateLivesLabel();
    }

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