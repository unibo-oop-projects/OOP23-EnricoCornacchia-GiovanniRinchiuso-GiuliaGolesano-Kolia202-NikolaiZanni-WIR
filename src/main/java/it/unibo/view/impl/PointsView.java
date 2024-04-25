package it.unibo.view.impl;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constaints;

public class PointsView extends StackPane {
    private PointsComponent pointsComponent;

    public PointsView(PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        updatePointsLabel();
    }

    public void updatePointsLabel() {
        ImageView pointsBackground = new ImageView("PointsTable.png");
        pointsBackground.setFitWidth(225);
        pointsBackground.setFitHeight(Constaints.HEIGHT_PAUSE_BUTTON);

        Label pointsLabel = new Label("score: " + pointsComponent.getPoints());
        pointsLabel.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: transparent;");
        pointsLabel.setPrefSize(pointsBackground.getFitWidth(), pointsBackground.getFitHeight());
        pointsLabel.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(pointsLabel, 10.0);
        AnchorPane.setBottomAnchor(pointsLabel, 25.0);

        AnchorPane pointsContainer = new AnchorPane(pointsBackground, pointsLabel);
        getChildren().add(pointsContainer);
    }
}