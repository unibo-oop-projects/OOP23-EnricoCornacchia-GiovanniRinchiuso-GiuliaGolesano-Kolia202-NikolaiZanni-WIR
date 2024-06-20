package it.unibo.view.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.impl.PointsComponent;

/**
 * PointsView represents the view for displaying the current score.
 * This class can be extended to customize the score display.
 */
public final class PointsView extends StackPane {
    private PointsComponent pointsComponent;
    private static final int PREWIDTH = 150;
    private static final int PREHEIGH = 25;
    private static final Double BOTTOM = 20.0;

    /**
     * Constructs a PointsView with the given PointsComponent.
     *
     * @param pointsComponent the PointsComponent to use for score data
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public PointsView(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        this.pointsComponent.addPointsView(this);
        updatePointsLabel();
    }

    /**
     * Updates the score label.
     */
    public void updatePointsLabel() {
        getChildren().clear();

        final Label scoreTitleLabel = new Label("SCORE:");
        final Label scoreValueLabel = new Label(" " + pointsComponent.getPoints());

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
     * Sets the PointsComponent to use for score data.
     *
     * @param pointsComponent the PointsComponent to use for score data
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public void setPointsComponent(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        this.pointsComponent.addPointsView(this);
        updatePointsLabel();
    }
}
