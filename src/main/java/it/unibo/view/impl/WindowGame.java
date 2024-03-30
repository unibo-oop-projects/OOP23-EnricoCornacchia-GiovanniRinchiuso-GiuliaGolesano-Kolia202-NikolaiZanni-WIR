package it.unibo.view.impl;

import javax.swing.JFrame;

public class WindowGame {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        PanelGame panel = new PanelGame();
        window.add(panel);

        window.setVisible(true);
        
    }
    
    
    
}
