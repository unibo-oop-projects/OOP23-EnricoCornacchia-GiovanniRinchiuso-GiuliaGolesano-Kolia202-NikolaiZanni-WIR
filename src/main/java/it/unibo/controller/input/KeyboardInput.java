package it.unibo.controller.input;


import java.awt.event.*;

import it.unibo.utilities.Movements;
import it.unibo.utilities.Constaints.MovementsKey;

public class KeyboardInput implements KeyListener{

    public boolean up, down, right, left, fix;

    @Override
    public void keyTyped(KeyEvent e) {
       }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int keyCode = e.getKeyCode();
        this.setBooleanValues(true, keyCode);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int keyCode = e.getKeyCode();
        this.setBooleanValues(false, keyCode);

    }

    private void setBooleanValues(boolean b, int KeyCode){
        switch (KeyCode) {
            //W 
            case MovementsKey.UP:
                up = b;
                if(b){
                    Movements.setMovements(Movements.UP);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA SU
            case MovementsKey.UP_ARROW:
                up = b;
                if(b){
                    Movements.setMovements(Movements.UP);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //A
            case MovementsKey.LEFT:
                left = b;
                if(b){
                    Movements.setMovements(Movements.LEFT);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA SINISTRA
            case MovementsKey.LEFT_ARROW:
                left = b;
                if(b){
                    Movements.setMovements(Movements.LEFT);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //S
            case MovementsKey.DOWN:
                down = b;
                if(b){
                    Movements.setMovements(Movements.DOWN);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA GIU
            case MovementsKey.DOWN_ARROW:
                down = b;
                if(b){
                    Movements.setMovements(Movements.DOWN);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //D
            case MovementsKey.RIGHT:
                right = b;
                if(b){
                    Movements.setMovements(Movements.RIGHT);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //FRECCIA DESTRA
            case MovementsKey.RIGHT_ARROW:
                right = b;
                if(b){
                    Movements.setMovements(Movements.RIGHT);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            //T
            case MovementsKey.FIX:
                fix = b;
                if(b){
                    Movements.setMovements(Movements.FIX);
                }else{
                    Movements.setMovements(Movements.STOP);
                }
                break;
            default:    Movements.setMovements(Movements.STOP);
                break;
        }
    }
    
}
