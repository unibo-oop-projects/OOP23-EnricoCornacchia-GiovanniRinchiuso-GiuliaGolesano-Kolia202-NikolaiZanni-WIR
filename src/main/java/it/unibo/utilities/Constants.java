package it.unibo.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.unibo.common.Pair;
import javafx.scene.image.Image;

/**
 * Class for the constaints used.
 */
public final class Constants {
    /**
     * Private constructor.
     */
    private Constants() {
    }
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
        public static final Pair<Double, Double> FELIX_START = new Pair<>(400.0, 560.0);
        /**
         * The width of Felix.
         */
        public static final double FELIX_WIDTH = 30.0;
        /**
         * The height of Felix.
         */
        public static final double FELIX_HEIGHT = 40.0;
    }
    /**
     * Ralph constaints.
     */
    public static class Ralph {
        /**
         * The position of starting Ralph of the first level.
         */
        public static final Pair<Double, Double> RALPH_START = new Pair<>(400.0, 40.0);
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
        public static final double RALPH_HEIGHT = 60;
        /**
         * The Ralph width.
         */
        public static final double RALPH_WIDTH = 80;
        /**
         * The Ralph speed in throwing bricks.
         */
        public static long THROW_TIME = 1000;
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
         * The speed of the brick at level 1.
        */
        public static final double BRICK_SPEED_LEVEL_1 = 1;
        /**
         * The speed of the brick at level 2.
        */
        public static final double BRICK_SPEED_LEVEL_2 = 2;
        /**
         * The speed of the brick at level 3.
        */
        public static final double BRICK_SPEED_LEVEL_3 = 3;
    }
    /**
     * Window constaints.
     */
    public static class Window {
        /**
         * The width of the window.
         */
        public static final double WINDOW_WIDTH = 40;
        /**
         * The height of the window.
         */
        public static final double WINDOW_HEIGHT = 40;
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
        public static final double BIRD_WIDTH = 100;
        /**
         * The height of the bird.
         */
        public static final double BIRD_HEIGHT = 100;
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
     * Power ups constains.
     */
    public static class PowerUps {
        /**
         * Starting x of the bird.
         */
        public static final double BIRD_MAX_X = 800.0;
        /**
         * Strating x of the bird.
         */
        public static final double BIRD_MIN_x = 0.0;
        /**
         * Minimum y of the bird.
         */
        public static final double BIRD_MIN_Y = 200.0;
        /**
         * Maximum y of the bird.
         */
        public static final double BIRD_MAX_Y = 600.0;
        /**
         * The position of the first floor cake y.
         */
        public static final double CAKE_FLOOR_1_Y = 200.0;
        /**
         * The position of the second floor cake y.
         */
        public static final double CAKE_FLOOR_2_Y = 400.0;
        /**
         * The position of the tird floor cake y.
         */
        public static final double CAKE_FLOOR_3_Y = 600.0;
        /**
         * Maximum x of the cake.
         */
        public static final double CAKE_MAX_X = 600.0;
        /**
         * Minimum y of the cake.
         */
        public static final double CAKE_MIN_X = 200.0;
        /**
         * The initial delay of the power ups.
         */
        public static final long INITIAL_DELAY = 5;
        /**
         * The period of the power ups.
         */
        public static final long PERIOD = 10;
    }
    /**
     * Floors constains.
     */
    public class Floors {
        /**
         * The position of the first floor.
         */
        public static final double FLOOR_1_Y = 200.0;
        /**
         * The position of the second floor.
         */
        public static final double FLOOR_2_Y = 400.0;
        /**
         * The position of the third floor.
         */
        public static final double FLOOR_3_Y = 600.0;
    }
    /**
     * Windows constains.
     */
    public static class Windows {
        /**
         * Number of broken windows in the first level.
         */
        public static final int BROKEN_1 = 5;
        /**
         * Number of broken windows in the second level.
         */
        public static final int BROKEN_2 = 8;
        /**
         * Number of broken windows in the third level.
         */
        public static final int BROKEN_3 = 10;
        /**
         * Number of broken windows in the fourth level.
         */
        public static final int BROKEN_4 = 13;
        /**
         * Number of broken windows in the fourth level.
         */
        public static final int NUM_WINDOWS = 15;
    }
    /**
     * Animations constaints.
     */
    public static class Animations{
        /**
         * Number of frames for Felix animation.
         */
        public static final int NUM_FRAMES_FELIX = 0;
        /**
         * Number of frames for Ralph animation.
         */
        public static final int NUM_FRAMES_RALPH = 0;
        /**
         * Number of frames for Brick fall animation.
         */
        public static final int NUM_FRAMES_BRICK = 0;
        /**
         * Number of frames for Cake spawn animation.
         */
        public static final int NUM_FRAMES_CAKE = 0;
        /**
         * Number of frames for Window fix animation.
         */
        public static final int NUM_FRAMES_WINDOW = 0;
        /**
         * Number of frames for Bird fly animation.
         */
        public static final int NUM_FRAMES_BIRD = 2;
        /**
         * Map of index and image.
         */
        public static final Map<Integer, Image> playerMap = new HashMap<>();
        /**
         * Getter for the map.
         */
        public static Map<Integer, Image> getPlayerMap() {
            return Collections.unmodifiableMap(playerMap);
        }   
    }
}
