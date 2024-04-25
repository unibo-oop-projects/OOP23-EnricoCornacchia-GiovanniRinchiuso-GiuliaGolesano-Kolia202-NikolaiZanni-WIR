package it.unibo.view.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import it.unibo.model.impl.PointsComponent;
import it.unibo.view.api.Listener;

public class PointsView extends StackPane implements Listener {
    private PointsComponent pointsComponent;
    private Label scoreValueLabel;

    public PointsView(PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        this.pointsComponent.registerPointsChangeListener(this);
        initializeUI();
    }

    private void initializeUI() {
        Label scoreTitleLabel = new Label("SCORE:");
        scoreValueLabel = new Label("" + pointsComponent.getPoints());
        scoreTitleLabel.setStyle(
                "-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: red; -fx-background-color: transparent;");
        scoreValueLabel.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: transparent;");

        scoreTitleLabel.setPrefWidth(150);
        scoreValueLabel.setPrefWidth(150);

        scoreTitleLabel.setPrefHeight(25);
        scoreValueLabel.setPrefHeight(25);

        AnchorPane.setBottomAnchor(scoreTitleLabel, 20.0);
        AnchorPane.setLeftAnchor(scoreTitleLabel, 0.0);
        AnchorPane.setBottomAnchor(scoreValueLabel, 0.0);
        AnchorPane.setLeftAnchor(scoreValueLabel, 0.0);

        AnchorPane pointsContainer = new AnchorPane(scoreTitleLabel, scoreValueLabel);
        getChildren().add(pointsContainer);
    }

    @Override
    public void onPointsChanged(int newPoints) {
        scoreValueLabel.setText("" + newPoints);
        System.out.println("Punteggio aggiornato: " + newPoints);
        scoreValueLabel.setText(Integer.toString(newPoints));
    }
}