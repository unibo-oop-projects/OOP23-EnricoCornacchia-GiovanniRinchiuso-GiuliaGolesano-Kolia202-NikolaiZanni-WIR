package it.unibo.core;

import it.unibo.controller.impl.ApplicationImpl;
import it.unibo.view.impl.GameApp;
import javafx.application.Application;

/**
 * Main class.
 */
public final class reckItRalph {

    private reckItRalph() {
    }

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new ApplicationImpl();
        Application.launch(GameApp.class);
    }
}