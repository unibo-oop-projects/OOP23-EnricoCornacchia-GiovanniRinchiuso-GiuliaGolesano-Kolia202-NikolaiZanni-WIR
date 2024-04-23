package it.unibo.view.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class EndGame extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Label message = new Label("GameOver, StartGame!");
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(new StackPane(message), 800, 600));
        primaryStage.setTitle("StartGame");
        primaryStage.show();
    }
}