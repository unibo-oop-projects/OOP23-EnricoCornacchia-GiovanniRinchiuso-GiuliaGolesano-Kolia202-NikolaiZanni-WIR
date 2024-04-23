package it.unibo.view.impl;

import it.unibo.model.impl.PointsComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * WindowGame, it represents the window of the game.
 */
public class WindowGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        PointsComponent pointsComponent = new PointsComponent();

        // Creazione di un'istanza di PointsView e passaggio di PointsComponent
        PointsView pointsView = new PointsView(pointsComponent);

        // Creazione di un'istanza di MainMenu
        MainMenu mainMenu = new MainMenu();

        // Creazione di un AnchorPane per posizionare i componenti
        AnchorPane root = new AnchorPane();
        AnchorPane.setRightAnchor(mainMenu, 0.0);
        AnchorPane.setTopAnchor(mainMenu, 0.0);
        AnchorPane.setLeftAnchor(pointsView, 0.0);
        AnchorPane.setTopAnchor(pointsView, 0.0);
        root.getChildren().addAll(mainMenu, pointsView);

        // Impostazione della scena e delle proprietà dello stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StartGame");
        primaryStage.show();
    }
}