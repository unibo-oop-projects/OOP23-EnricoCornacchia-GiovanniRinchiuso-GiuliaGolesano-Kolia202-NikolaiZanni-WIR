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
    private PointsComponent pointsComponent;
    private static final int PREWIDTH = 150;
    private static final int PREHEIGH = 25;
    private static final Double BOTTOM = 20.0;

    /**
     * Constructs a HighPointsView with the given PointsComponent.
     *
     * @param pointsComponent the PointsComponent to use for high score data
     */
    public HighPointsView(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        this.pointsComponent.addHighPointsView(this);
        updateHighPointsLabel();
    }

    /**
     * Updates the high score label.
     */
    public final void updateHighPointsLabel() {
        getChildren().clear();

        final Label scoreTitleLabel = new Label("HIGHSCORE:");
        final Label scoreValueLabel = new Label(" " + pointsComponent.getHighScore());

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

        final AnchorPane pointsContainer = new AnchorPane(scoreTitleLabel, scoreValueLabel);
        getChildren().add(pointsContainer);
    }
    /**
     * Sets the PointsComponent to use for high score data.
     *
     * @param pointsComponent the PointsComponent to use for high score data
     */
    public void setPointsComponent(PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        this.pointsComponent.addHighPointsView(this);
        updateHighPointsLabel();
    }
}
