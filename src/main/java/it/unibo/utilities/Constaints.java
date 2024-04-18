package it.unibo.utilities;

import it.unibo.common.Pair;

/**
 * Class for the constaints used.
 */
public final class Constaints {
    /**
     * The name of the gamestate.
     */
    public static final String GAMESTATE = "GameState: ";
     /**
     * The speed of the brick.
     */
    public static final double BRICK_SPEED = 1.0;
    /**
     * The position of the left wall.
     */
    public static final double LEFT_WALL = 0.0;
    /**
     * The position of the right wall.
     */
    public static final double RIGHT_WALL = 800.0;
    /**
     * The position of the down wall.
     */
    public static final double DOWN_WALL = 0.0;
    /**
     * The position of the up wall.
     */
    public static final double UP_WALL = 600.0;
    /**
     * The position of starting Felix.
     */
    public static final Pair<Double, Double> FELIX_START = new Pair<>(400.0, 0.0);
    /**
     * The position of starting Ralph.
     */
    public static final Pair<Double, Double> RALPH_START = new Pair<>(400.0, 700.0);
    /**
     * The difference between the position of Ralph and the position of his right
     * hand.
     */
    public static final Pair<Double, Double> RALPH_RIGHT_HAND = new Pair<>(8.0, -2.0);
    /**
     * The difference between the position of Ralph and the position of his left
     * hand.
     */
    public static final Pair<Double, Double> RALPH_LEFT_HAND = new Pair<>(-8.0, -2.0);
    /**
     * The position of the house button.
     */
    public static final Pair<Double, Double> HOME_BUTTON = new Pair<>(null, null);
    /**
     * The position of the Pause button.
     */
    public static final Pair<Double, Double> PAUSE_BUTTON = new Pair<>(null, null);
    /**
     * The position of the house button.
     */
    public static final Pair<Double, Double> QUIT_BUTTON = new Pair<>(null, null);
    /**
     * The position of the Pause button.
     */
    public static final Pair<Double, Double> CONTINUE_BUTTON = new Pair<>(null, null);
    /**
     * The height of the top image.
     */
    public static final double TOP_IMAGE_HEIGHT = 125;
    /**
     * The width of the top image.
     */
    public static final double TOP_IMAGE_WIDTH = 450;
    /**
     * The height of the pause button.
     */
    public static final double WIDTH_PAUSE_BUTTON = 50;
    /**
     * The width of the pause button.
     */
    public static final double HEIGHT_PAUSE_BUTTON = 50;
    /**
     * The hight of the under image.
     */
    public static final double UNDER_IMAGE_HEIGHT = 200;
    /**
     * The width of the under image.
     */
    public static final double UNDER_IMAGE_WIDTH = 450;
    private Constaints() {
    }
    /**
     * keys of movement.
     */
    public static class MovementsKey {
        /**
         * Pressed Key A.
         */
        public static final int LEFT = 65;
        /**
         * Pressed Left arrow.
         */
        public static final int LEFT_ARROW = 37;
        /**
         * Pressed Key D.
         */
        public static final int RIGHT = 68;
        /**
         * Pressed Right arrow.
         */
        public static final int RIGHT_ARROW = 39;
        /**
         * Pressed Key W.
         */
        public static final int UP = 87;
        /**
         * Pressed Up arrow.
         */
        public static final int UP_ARROW = 38;
        /**
         * Pressed Key S.
         */
        public static final int DOWN = 83;
        /**
         * Pressed Down arrow.
         */
        public static final int DOWN_ARROW = 40;
        /**
         * Pressed Key T.
         */
        public static final int FIX = 84;
        /**
         * Pressed Esc.
         */
        public static final int ESCAPE = 27;
    }
}
