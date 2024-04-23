package it.unibo.view.impl;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import it.unibo.model.impl.PointsComponent;

public class PointsView extends StackPane {

    private PointsComponent pointsComponent;

    public PointsView(PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        updatePointsLabel();
    }

    private void updatePointsLabel() {
        ImageView pointsBackground = new ImageView("");
        pointsBackground.setFitWidth(100);
        pointsBackground.setFitHeight(100);

        Label pointsLabel = new Label("Points: " + pointsComponent.getPoints());
        pointsLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: transparent;");
        pointsLabel.setPrefSize(pointsBackground.getFitWidth(), pointsBackground.getFitHeight());
        pointsLabel.setAlignment(Pos.CENTER);

        StackPane pointsContainer = new StackPane(pointsBackground, pointsLabel);
        getChildren().add(pointsContainer);
    }
}