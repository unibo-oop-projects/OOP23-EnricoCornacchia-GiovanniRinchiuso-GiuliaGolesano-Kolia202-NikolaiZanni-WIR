package it.unibo.core;

import it.unibo.controller.impl.GameController;
import it.unibo.view.impl.GameApp;
import javafx.application.Application;

/**
 * Main class.
 */
public final class ReckItRalph {

    private ReckItRalph() {
    }

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new GameController();
        Application.launch(GameApp.class);
    }
}
