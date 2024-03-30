package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelGame extends JPanel{

    //game window dimensionsk
    final int tilePixels = 16;
    final int scale = 3;
    final int col = 12;
    final int row = 16;
    final int height = tilePixels * scale * row;
    final int width = tilePixels * scale * col;

    public PanelGame(){
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }
    
}
