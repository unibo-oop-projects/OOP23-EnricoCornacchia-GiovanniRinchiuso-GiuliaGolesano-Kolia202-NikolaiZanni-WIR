package it.unibo.view.impl;

import javax.swing.JFrame;
/**
 * WindowGame, it represents the game window.
 */
public class WindowGame {
    /**
     * main, it starts the game window.
     * @param args the arguments of the main.
     */
    public static void main(final String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        PanelGame panel = new PanelGame();
        window.add(panel);
        window.setVisible(true);
    }  
}
