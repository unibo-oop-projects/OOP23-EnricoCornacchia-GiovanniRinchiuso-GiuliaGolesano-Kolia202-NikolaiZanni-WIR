package it.unibo.view.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import it.unibo.model.impl.PointsComponent;

/**
 * HighPointsView represents the view for displaying the high score.
 * This class can be extended to customize the high score display.
 */
public class HighPointsView extends StackPane {
    private final PointsComponent pointsComponent;
    private final int PREWIDTH = 150;
    private final int PREHEIGH = 25;
    private final Double BOTTOM = 20.0;
    private Label scoreValueLabel;

    /**
     * Constructs a HighPointsView with the given PointsComponent.
     *
     * @param pointsComponent the PointsComponent to use for high score data
     */
    public HighPointsView(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        updateHighPointsLabel();
    }

    /**
     * Updates the high score label.
     */
    public void updateHighPointsLabel() {
        getChildren().clear(); // Clear existing labels before updating

        Label scoreTitleLabel = new Label("HIGHSCORE:");
        scoreValueLabel = new Label("" + pointsComponent.getHighScore());

        scoreTitleLabel.setStyle(
                "-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: red; -fx-background-color: transparent;");
        scoreValueLabel.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: transparent;");

        scoreTitleLabel.setPrefWidth(PREWIDTH);
        scoreValueLabel.setPrefWidth(PREWIDTH);

        scoreTitleLabel.setPrefHeight(PREHEIGH);
        scoreValueLabel.setPrefHeight(PREHEIGH);

        AnchorPane.setBottomAnchor(scoreTitleLabel, BOTTOM);
        AnchorPane.setLeftAnchor(scoreTitleLabel, 0.0);
        AnchorPane.setBottomAnchor(scoreValueLabel, 0.0);
        AnchorPane.setLeftAnchor(scoreValueLabel, 0.0);

        AnchorPane pointsContainer = new AnchorPane(scoreTitleLabel, scoreValueLabel);
        getChildren().add(pointsContainer);
    }
}