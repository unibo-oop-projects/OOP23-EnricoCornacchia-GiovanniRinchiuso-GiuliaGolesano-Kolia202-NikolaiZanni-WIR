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
     * Constaints of the edges positions.
     */
    public static class GameEdges {
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
        public static final double UP_WALL_1 = 600.0;
    }
    /**
     * Felix constaints.
     */
    public static class Felix {
        /**
         * The position of starting Felix.
         */
        public static final Pair<Double, Double> FELIX_START = new Pair<>(400.0, 0.0);
        /**
         * The width of Felix.
         */
        public static final double FELIX_WIDTH = 20.0;
        /**
         * The height of Felix.
         */
        public static final double FELIX_HEIGHT = 20.0;
    }
    /**
     * Ralph constaints.
     */
    public static class Ralph {
        /**
         * The position of starting Ralph of the first level.
         */
        public static final Pair<Double, Double> RALPH_START_LEVEL_1 = new Pair<>(400.0, 700.0);
        /**
         * The position of starting Ralph of the second level.
         */
        public static final Pair<Double, Double> RALPH_START_LEVEL_2 = new Pair<>(400.0, 700.0);
        /**
         * The position of starting Ralph of the third level.
         */
        public static final Pair<Double, Double> RALPH_START_LEVEL_3 = new Pair<>(400.0, 700.0);
        /**
         * The position of starting Ralph of the fourth level.
         */
        public static final Pair<Double, Double> RALPH_START_LEVEL_4 = new Pair<>(400.0, 700.0);
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
         * The Ralph height.
         */
        public static final double RALPH_HEIGHT = 0;
        /**
         * The Ralph width.
         */
        public static final double RALPH_WIDTH = 0;
    }
    /**
     * Brick constaints.
     */
    public static class Brick {
        /**
         * The width of the brick.
         */
        public static final double BRICK_WIDTH = 0;
        /**
         * The height of the brick.
         */
        public static final double BRICK_HEIGHT = 0;
        /**
         * The speed of the brick.
        */
        public static final double BRICK_SPEED = 1.0;
    }
    /**
     * Window constaints.
     */
    public static class Window {
        /**
         * The width of the window.
         */
        public static final double WINDOW_WIDTH = 0;
        /**
         * The height of the window.
         */
        public static final double WINDOW_HEIGHT = 0;
    }
    /**
     * Cake constaints.
     */
    public static class Cake {
        /**
         * The width of the cake.
         */
        public static final double CAKE_WIDTH = 0;
        /**
         * The height of the cake.
         */
        public static final double CAKE_HEIGHT = 0;
    }
    /**
     * Bird constaints.
     */
    public static class Bird {
        /**
         * The width of the bird.
         */
        public static final double BIRD_WIDTH = 0;
        /**
         * The height of the bird.
         */
        public static final double BIRD_HEIGHT = 0;
    }
    /**
     * Buttons constaints.
     */
    public static class Button {
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
        public static final double WIDTH_PAUSE_BUTTON = 40;
        /**
         * The width of the pause button.
         */
        public static final double HEIGHT_PAUSE_BUTTON = 40;
        /**
         * The hight of the under image.
         */
        public static final double UNDER_IMAGE_HEIGHT = 200;
        /**
         * The width of the under image.
         */
        public static final double UNDER_IMAGE_WIDTH = 450;
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
    /**
     * Power ups constains.
     */
    public static class PowerUps {
        /**
         * Starting x of the bird.
         */
        public static final double BIRD_X = 800.0;
        /**
         * Minimum y of the bird.
         */
        public static final double BIRD_MIN_Y = 50.0;
        /**
         * Maximum y of the bird level 1.
         */
        public static final double BIRD_MAX_Y_1 = 600.0;
        /**
         * Maximum y of the bird level 2.
         */
        public static final double BIRD_MAX_Y_2 = 0.0;
        /**
         * Maximum y of the bird level 3.
         */
        public static final double BIRD_MAX_Y_3 = 0.0;
        /**
         * Maximum y of the bird level 4.
         */
        public static final double BIRD_MAX_Y_4 = 0.0;
    }
}
