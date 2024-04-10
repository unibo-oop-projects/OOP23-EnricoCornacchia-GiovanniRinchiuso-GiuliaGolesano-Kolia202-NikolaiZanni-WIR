package it.unibo.utilities;
/**
 * Different states of the game.
 */
public enum GameState {
    HOME,

    PLAYING,

    PAUSED,

    GAMEOVER,

    SETTINGS;

    private static GameState gamestate = HOME;
    /**
     * Get the current game state.
     * @return the current game state
     */
    public static GameState getGameState() {
        return gamestate;
    }
    /**
     * Set the game state.
     * @param state the game state
     */
    public static void setGameState(final GameState state) {
        gamestate = state;
    }
}