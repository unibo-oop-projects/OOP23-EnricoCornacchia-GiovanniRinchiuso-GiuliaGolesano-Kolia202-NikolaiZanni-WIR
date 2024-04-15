package it.unibo.view.impl;

import javax.swing.JFrame;
/**
 * WindowGame, it represents the game window.
 */
public final class WindowGame {
    private WindowGame() {
    }
    /**
     * main, it starts the game window.
     * 
     * @param args the arguments of the main.
     * @throws Exception if an error occurs.
     */
    public static void main(final String[] args) throws Exception {
        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        final PanelGame panel = new PanelGame();
        window.add(panel);
        window.setVisible(true);
    }
}
