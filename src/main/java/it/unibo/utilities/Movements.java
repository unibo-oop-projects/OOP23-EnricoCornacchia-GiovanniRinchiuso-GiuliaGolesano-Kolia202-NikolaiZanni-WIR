package it.unibo.utilities;

public enum Movements {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    STOP;

    public static Movements current = STOP;

    public static Movements getMovements(){
        return current;
    }

    public static void setMovements(final Movements mov){
        current=mov;
    }

    
}
