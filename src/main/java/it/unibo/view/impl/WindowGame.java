package it.unibo.view.impl;

import javax.swing.JFrame;

public class WindowGame {
    /**
     * main, it starts the game window.
     * 
     * @param args the arguments of the main.
     * @throws Exception if an error occurs.
     */
    public static void main(final String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        PanelGame panel = new PanelGame();
        window.add(panel);
        window.setVisible(true);
    }
}
