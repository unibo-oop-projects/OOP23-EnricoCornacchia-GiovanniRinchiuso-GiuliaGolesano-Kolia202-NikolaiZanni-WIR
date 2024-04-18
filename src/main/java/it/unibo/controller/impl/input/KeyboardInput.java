package it.unibo.controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import it.unibo.utilities.Movements;
import it.unibo.utilities.Constaints.MovementsKey;
import javafx.application.Application;

/**
 * Class that manages the keyboard input.
 */
public class KeyboardInput implements KeyListener {

    private boolean up, down, right, left, fix;
    private Application app;

    /**
     * Constructor.
     */
    public KeyboardInput(final Application app) {
        this.app = app;
    }

    /**
     * Getter of the variable up.
     * 
     * @return up
     */
    public boolean isUp() {
        return this.up;
    }

    /**
     * Getter of the variable down.
     * 
     * @return down
     */
    public boolean isDown() {
        return this.down;
    }

    /**
     * Getter of the variable right.
     * 
     * @return right
     */
    public boolean isRight() {
        return this.right;
    }

    /**
     * Getter of the variable left.
     * 
     * @return left
     */
    public boolean isLeft() {
        return this.left;
    }

    /**
     * Getter of the variable fix.
     * 
     * @return fix
     */
    public boolean isFix() {
        return this.fix;
    }

    @Override
    /**
     * Method called when a button of the keyboard is typed.
     * 
     * @param e is the KeyEvent
     */
    public void keyTyped(final KeyEvent e) {}

    /**
     * Method called when a button of the keyboard is pressed.
     * 
     * @param e the KeyEvent
     */
    public void keyPressed(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.setBooleanValues(true, keyCode);
    }

    /**
     * Method called when a button of the keyboard is released after being pressed.
     * 
     * @param e the KeyEvent
     */
    public void keyReleased(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.setBooleanValues(false, keyCode);
    }

    private void setBooleanValues(final boolean b, final int keyCode) {
        switch (keyCode) {
            // W
            case MovementsKey.UP:
                up = b;
                if (b) {
                    Movements.setMovements(Movements.UP);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // FRECCIA SU
            case MovementsKey.UP_ARROW:
                up = b;
                if (b) {
                    Movements.setMovements(Movements.UP);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // A
            case MovementsKey.LEFT:
                left = b;
                if (b) {
                    Movements.setMovements(Movements.LEFT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // FRECCIA SINISTRA
            case MovementsKey.LEFT_ARROW:
                left = b;
                if (b) {
                    Movements.setMovements(Movements.LEFT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // S
            case MovementsKey.DOWN:
                down = b;
                if (b) {
                    Movements.setMovements(Movements.DOWN);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // FRECCIA GIU
            case MovementsKey.DOWN_ARROW:
                down = b;
                if (b) {
                    Movements.setMovements(Movements.DOWN);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // D
            case MovementsKey.RIGHT:
                right = b;
                if (b) {
                    Movements.setMovements(Movements.RIGHT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // FRECCIA DESTRA
            case MovementsKey.RIGHT_ARROW:
                right = b;
                if (b) {
                    Movements.setMovements(Movements.RIGHT);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            // T
            case MovementsKey.FIX:
                fix = b;
                if (b) {
                    Movements.setMovements(Movements.FIX);
                } else {
                    Movements.setMovements(Movements.STOP);
                }
                break;
            default:
                Movements.setMovements(Movements.STOP);
                break;
        }
    }
}
