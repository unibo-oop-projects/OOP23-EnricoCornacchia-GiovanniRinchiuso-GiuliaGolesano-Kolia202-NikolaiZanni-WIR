package it.unibo.view.impl;



import it.unibo.utilities.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
    public static MenuBar createPauseMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Pause menu");
        MenuItem resumeItem = new MenuItem("Resume game");
        resumeItem.setOnAction(event -> {
            GameState.setGameState(GameState.PLAYING);
            // Codice per riprendere il gioco
        });

        MenuItem mainMenuItem = new MenuItem("Main Menu");
        mainMenuItem.setOnAction(event -> {
            GameState.setGameState(GameState.HOME);
            // Codice per tornare al menu principale
        });

        MenuItem settingsItem = new MenuItem("Settings");
        settingsItem.setOnAction(event -> {
            GameState.setGameState(GameState.SETTINGS);
            // Codice per aprire le impostazioni
        });

        menu.getItems().addAll(resumeItem, mainMenuItem, settingsItem);
        menuBar.getMenus().add(menu);

        return menuBar;
    }

    @Override
    public void start(Stage primaryStage) {
        // Creazione della barra del menu di pausa
        VBox root = new VBox();
        root.getChildren().add(MainMenu.createPauseMenu());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launchMenu(String[] args) {
        launch(args);
    }

    public static void run(final String... args) {
        launch(args);
    }

    public static final class Main {
        private Main() {
        }

        public static void main(final String... args) {
            Application.launch(MainMenu.class, args);
        }
    }
}
  
