package it.unibo.utilities;
/**
 * Represents the movements of the player.
 */
public enum Movements {

    /**
     * Represents the upward movement of the player.
     */
    UP,
    /**
     * Represents the downward movement of the player.
     */
    DOWN,
    /**
     * Represents the moving to the left of the player.
     */
    LEFT,
    /**
     * Represents the moving to the right of the player.
     */
    RIGHT,
    /**
     * Represents the movements to fit the window of the player.
     */
    FIX,
    /**
     * Represents the motionlessness of the player.
     */
    STOP;
    /**
     * The current movements.
     */
    private static Movements current = STOP;
    /**
     * Get the current movement.
     * @return the current movement
     */
    public static Movements getMovement() {
        return current;
    }
    /**
     * Set the movement.
     * @param mov is the movement to set
     */
    public static void setMovement(final Movements mov) {
        current = mov;
    }
}
