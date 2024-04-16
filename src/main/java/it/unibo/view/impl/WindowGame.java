package it.unibo.view.impl;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
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
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            final PanelGame panel = new PanelGame();
            window.add(panel);
            window.pack(); // Pack the components to fit their preferred size
            window.setLocationRelativeTo(null); // Center the window on the screen
            window.setVisible(true);
        });
    }
}
