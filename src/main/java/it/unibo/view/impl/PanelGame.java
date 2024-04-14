package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
/**
 * PanelGame, it represents the game panel.
 */
public class PanelGame extends JPanel {

    //game window dimensionsk
    private final int tilePixels = 16;
    private final int scale = 3;
    private final int col = 12;
    private final int row = 16;
    private final int height = tilePixels * scale * row;
    private final int width = tilePixels * scale * col;
    /**
     * Constructor of the class.
     */
    public PanelGame() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
