package it.unibo.view.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Image config = new Image("config.png");'
        //ImageView configView = new ImageView(config);
        final Button configButton = new Button("CONFIG");
        configButton.setFont(new Font(100));
        configButton.setOnMouseClicked(mouseEvent -> {
            new AnotherStage().show();
        });
        primaryStage.setScene(new Scene(configButton));
        primaryStage.setTitle("PROVA");
        primaryStage.show();
    }
    private static void run(final String[] args) {
        launch(args);
    }
    public static final class Main {
        private Main() {
        }
    
    public static void main(final String... args) {
        MainMenu.run(args);
    }
}

    private static class AnotherStage extends Stage {
        private static final int WIDTH = 100;
        private static final int HEIGHT = 100;

        AnotherStage() {
            super();
            setTitle("new Stage created" + System.currentTimeMillis());
            final VBox pane = new VBox();
            pane.getChildren().add(new Label("RESUME"));
            pane.getChildren().add(new Label("QUIT"));
            pane.getChildren().add(new Label("HOME"));
            setScene(new Scene(pane, WIDTH, HEIGHT));
        }
    }
}

  
