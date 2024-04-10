package it.unibo.utilities;
/**
 * Represents the movements of the player.
 */
public enum Movements {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    FIX,
    STOP;

    public static Movements current = STOP;
    /**
     * Get the current movements.
     * @return the current movements
     */
    public static Movements getMovements(){
        return current;
    }
    /**
     * Set the movements.
     * @param mov the movements
     */
    public static void setMovements(final Movements mov){
        current=mov;
    }

    
}
