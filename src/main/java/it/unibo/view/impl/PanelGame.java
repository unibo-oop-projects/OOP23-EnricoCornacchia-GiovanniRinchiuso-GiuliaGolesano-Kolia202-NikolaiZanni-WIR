package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * PanelGame, it represents the game panel.
 */
public class PanelGame extends JPanel {

    private static final long serialVersionUID = 1L;

    // game window dimensionsk
    private static final int TILE_PIXELS = 16;
    private static final int SCALE = 3;
    private static final int COL = 12;
    private static final int ROW = 16;
    private final int height = TILE_PIXELS * SCALE * ROW;
    private final int width = TILE_PIXELS * SCALE * COL;

    /**
     * Constructor of the class.
     */
    public PanelGame() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
