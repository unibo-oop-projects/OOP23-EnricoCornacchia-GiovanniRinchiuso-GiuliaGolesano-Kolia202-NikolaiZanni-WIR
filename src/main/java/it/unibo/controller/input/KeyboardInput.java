package it.unibo.controller.input;

import java.awt.event.*;
import it.unibo.utilities.Movements;
import it.unibo.utilities.Constaints.MovementsKey;
/**
 * Class that manages the keyboard input.
 */
public class KeyboardInput implements KeyListener {

    private boolean up, down, right, left, fix;
    
    /**
    * Getter of the variable up
    * return up
    */
    public boolean getUp(){
        return this.up;
    }
    
    /**
    * Getter of the variable down
    * return down
    */
    public boolean getDown(){
        return this.down;
    }

    /**
    * Getter of the variable right
    * return right
    */
    public boolean getRight(){
        return this.right;
    }

    /**
    * Getter of the variable left
    * return left
    */
    public boolean getLeft(){
        return this.left;
    }

    /**
    * Getter of the variable fix
    * return fix
    */
    public boolean getFix(){
        return this.fix;
    }

    @Override
    /**
     * Method called when a button of the keyboard is typed.
     * @param e
     */
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    /**
     * Method called when a button of the keyboard is pressed.
     * @param e
     */
    public void keyPressed(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.setBooleanValues(true, keyCode);
    }

    @Override
    /**
     * Method called when the button of the keyboard is released after being pressed.
     * @param e
     */
    public void keyReleased(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.setBooleanValues(false, keyCode);
    }
    /**
     * Method that set the boolean values about the movements and calls the Movements enum methods.
     * @param b
     * @param KeyCode
     */
    private void setBooleanValues(final boolean b, final int keyCode) {
        switch (keyCode) {
            //W 
            case MovementsKey.UP:
                up = b;
                if (b) {
                    Movements.setMovements(Movements.UP);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA SU
            case MovementsKey.UP_ARROW:
                up = b;
                if (b) {
                    Movements.setMovements(Movements.UP);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //A
            case MovementsKey.LEFT:
                left = b;
                if (b) {
                    Movements.setMovements(Movements.LEFT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA SINISTRA
            case MovementsKey.LEFT_ARROW:
                left = b;
                if (b) {
                    Movements.setMovements(Movements.LEFT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //S
            case MovementsKey.DOWN:
                down = b;
                if (b) {
                    Movements.setMovements(Movements.DOWN);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA GIU
            case MovementsKey.DOWN_ARROW:
                down = b;
                if (b) {
                    Movements.setMovements(Movements.DOWN);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //D
            case MovementsKey.RIGHT:
                right = b;
                if (b) {
                    Movements.setMovements(Movements.RIGHT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA DESTRA
            case MovementsKey.RIGHT_ARROW:
                right = b;
                if (b) {
                    Movements.setMovements(Movements.RIGHT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //T
            case MovementsKey.FIX:
                fix = b;
                if (b) {
                    Movements.setMovements(Movements.FIX);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            default:    Movements.setMovements(Movements.STOP);
                break;
        }
    }
}
