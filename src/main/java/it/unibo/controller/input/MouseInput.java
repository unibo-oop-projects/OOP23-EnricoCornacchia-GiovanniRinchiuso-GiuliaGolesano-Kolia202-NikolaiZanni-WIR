package it.unibo.controller.input;

import java.awt.event.*;
import it.unibo.common.Pair;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.GameState;

public class MouseInput implements MouseListener, MouseMotionListener{

    private Pair<Double, Double> pos;
    private boolean hasClicked;
    private boolean hasPressed;

    public MouseInput(){
        pos= new Pair<Double,Double>(0.0,0.0);
    }

    /**
     * Getter of the position.
     * @return the position of the mouse
     */
    public Pair<Double,Double> getPosition(){
        return this.pos;
    }

    /**
     * Getter of the boolean hasClicked.
     * @return if the mouse has clicked
     */
    public boolean getClick(){
        return this.hasClicked;
    }

    /**
     * Getter fo the boolean hasPressed.
     * @return if the mouse has clicked
     */
    public boolean getPress(){
        return this.hasPressed;
    }

    /**
     * Method that makes sure that hasClicked don’t always stay true
     */
    public void setClick(){
        this.hasClicked = false;
    }

    @Override
    /**
     * Method invoked when the mouse button has been clicked on a component.
     * @param e
     */
    public void mouseClicked(MouseEvent e) {}

    @Override
    /**
     * Method invoked when the mouse button has been pressed on a component.
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        this.hasPressed=true;
    }

    @Override
    /** 
     * Method invoked when the mouse button has been released on a component.
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        this.hasPressed=false;
        this.hasClicked=true;
    }

    @Override
    /**
     * Method invoked when the mouse enters a component.
     * @param e
     */
    public void mouseEntered(MouseEvent e) {}

    @Override
    /**
     * Method invoke when the mouse exits a component.
     * @param e
     */
    public void mouseExited(MouseEvent e) {}

    @Override
    /**
     * Method that set the new position of the mouse.
     * @param e
     */
    public void mouseDragged(MouseEvent e) {
        this.pos = new Pair<Double,Double>(Double.valueOf(e.getX()), Double.valueOf(e.getY()));
        
    }

    @Override
    /**
     * Method that set the new position of the mouse if it has moved.
     * @param e
     */
    public void mouseMoved(MouseEvent e) {
        this.pos = new Pair<Double,Double>(Double.valueOf(e.getX()), Double.valueOf(e.getY()));
    }

    /**
     * Method that determines whether and in which button we are.
     * @return the new GameState of the button clicked
     */
    public GameState buttonClicked(){
        if(this.pos == Constaints.HOME_BUTTTON){
            GameState.setGameState(GameState.HOME);
        }
        if(this.pos == Constaints.HOME_BUTTTON){
            GameState.setGameState(GameState.HOME);
        }
        if(this.pos == Constaints.HOME_BUTTTON){
            GameState.setGameState(GameState.HOME);
        }
        if(this.pos == Constaints.HOME_BUTTTON){
            GameState.setGameState(GameState.HOME);
        }
        return GameState.getGameState();
    }
}
