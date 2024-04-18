package it.unibo.controller.input;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import it.unibo.common.Pair;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.GameState;

/**
 * Class that manages the mouse input.
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    private Pair<Double, Double> pos;
    private boolean hasClicked;
    private boolean hasPressed;

    /**
    * Constructor of the class.
    */
    public MouseInput() {
        pos = new Pair<>(0.0, 0.0);
    }

    /**
     * Getter of the position.
     * 
     * @return the position of the mouse
     */
    public Pair<Double, Double> getPosition() {
        return this.pos;
    }

    /**
     * Getter of the boolean hasClicked.
     * 
     * @return if the mouse has clicked
     */
    public boolean isClicked() {
        return this.hasClicked;
    }

    /**
     * Getter fo the boolean hasPressed.
     * 
     * @return if the mouse has clicked
     */
    public boolean isPressed() {
        return this.hasPressed;
    }

    /**
     * Method that makes sure that hasClicked don’t always stay true.
     */
    public void setClick() {
        this.hasClicked = false;
    }

    @Override
    /**
     * Method invoked when the mouse button has been clicked on a component.
     * 
     * @param e
     */
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    /**
     * Method invoked when the mouse button has been pressed on a component.
     * 
     * @param e
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        this.hasPressed = true;
    }

    @Override
    /**
     * Method invoked when the mouse button has been released on a component.
     * 
     * @param e
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        this.hasPressed = false;
        this.hasClicked = true;
    }

    @Override
    /**
     * Method invoked when the mouse enters a component.
     * 
     * @param e
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    /**
     * Method invoke when the mouse exits a component.
     * 
     * @param e
     */
    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    /**
     * Method that set the new position of the mouse.
     * 
     * @param e
     */
    @Override
    public void mouseDragged(final MouseEvent e) {
        this.pos = new Pair<>(Double.valueOf(e.getX()), Double.valueOf(e.getY())); 
    }

    @Override
    /**
     * Method that set the new position of the mouse if it has moved.
     * 
     * @param e
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        this.pos = new Pair<>(Double.valueOf(e.getX()), Double.valueOf(e.getY()));
    }

    /**
     * Method that determines whether and in which button we are.
     * 
     * @return the new GameState of the button clicked
     */
    public GameState buttonClicked() {
        if (this.pos.equals(Constaints.HOME_BUTTON)) {
            GameState.setGameState(GameState.HOME);
        }
        if (this.pos.equals(Constaints.QUIT_BUTTON)) {
            GameState.setGameState(GameState.GAMEOVER);
        }
        if (this.pos.equals(Constaints.CONTINUE_BUTTON)) {
            GameState.setGameState(GameState.PLAYING);
        }
        if (this.pos.equals(Constaints.PAUSE_BUTTON)) {
            GameState.setGameState(GameState.PAUSED);
        }
        return GameState.getGameState();
    }
}
