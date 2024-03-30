package it.unibo.controller.input;

import java.awt.event.*;

import it.unibo.utilities.GameState;


public class MouseInput implements MouseListener{

    //in base al bottone cliccato, bisognerà cambiare o richiamare metodi del gamestate
    //PAUSE, QUIT, PLAY, LEVEL?, 

    @Override
    public void mouseClicked(MouseEvent e) {
        //invoked when the mouse button has been clicked on a component --> pressed and released
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //invoked when the mouse button has been pressed on a component
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       //invoked when the mouse button has been released on a component 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //invoked when the mouse enters a component
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //invoke when the mouse exits a component
    }


}
