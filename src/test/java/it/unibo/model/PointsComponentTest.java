package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.PointsComponent;

/**
 * Class to test the Points Component.
 */
public final class PointsComponentTest {

    private static final String FILENAME = "src/main/java/it/unibo/model/impl/scores.txt";
    private static final int INITIAL_POINTS = 0;
    private static final int ADD_POINTS_10 = 10;
    private static final int ADD_POINTS_5 = 5;
    private static final int ADD_POINTS_20 = 20;
    private static final int ADD_POINTS_MINUS_10 = -10;
    private static final int EXPECTED_HIGH_SCORE_15 = 15;
    private static final int EXPECTED_HIGH_SCORE_40 = 40;
    private static final int EXPECTED_POINTS_5 = 5;
    private static final int EXPECTED_POINTS_15 = 15;

    private PointsComponent pointsComponent;

    /**
     * Set up the PointsComponent before each test.
     */
    @BeforeEach
    void setUp() {
        pointsComponent = new PointsComponent();
    }

    /**
     * Test reading from the file.
     */
    @Test
    public void testReadFromFile() {
        pointsComponent.readFromFile();
        assertEquals(INITIAL_POINTS, pointsComponent.getHighScore());
        pointsComponent.addPoints(ADD_POINTS_20);
        pointsComponent.readFromFile();
        assertEquals(ADD_POINTS_20, pointsComponent.getHighScore());
        pointsComponent.addPoints(ADD_POINTS_MINUS_10);
        pointsComponent.readFromFile();
        assertEquals(ADD_POINTS_20, pointsComponent.getHighScore());
    }

    /**
     * Test getting the current points.
     */
    @Test
    public void testGetPoints() {
        assertEquals(INITIAL_POINTS, pointsComponent.getPoints());
    }

    /**
     * Test getting the high score.
     */
    @Test
    public void testGetHighScore() {
        assertEquals(INITIAL_POINTS, pointsComponent.getHighScore());
    }

    /**
     * Test adding points.
     */
    @Test
    public void testAddPoints() {
        pointsComponent.addPoints(ADD_POINTS_10);
        assertEquals(ADD_POINTS_10, pointsComponent.getPoints());
        pointsComponent.addPoints(ADD_POINTS_5);
        assertEquals(EXPECTED_POINTS_15, pointsComponent.getPoints());
        assertEquals(EXPECTED_HIGH_SCORE_15, pointsComponent.getHighScore());
        pointsComponent.addPoints(ADD_POINTS_MINUS_10);
        assertEquals(EXPECTED_POINTS_5, pointsComponent.getPoints());
        assertEquals(EXPECTED_HIGH_SCORE_15, pointsComponent.getHighScore());
    }

    /**
     * Test writing to the file.
     */
    @Test
    public void testWriteToFile() {
        pointsComponent.addPoints(ADD_POINTS_20);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILENAME))).trim();
            assertEquals(String.valueOf(ADD_POINTS_20), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointsComponent.addPoints(ADD_POINTS_20);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILENAME))).trim();
            assertEquals(String.valueOf(EXPECTED_HIGH_SCORE_40), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointsComponent.addPoints(ADD_POINTS_MINUS_10);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILENAME))).trim();
            assertEquals(String.valueOf(EXPECTED_HIGH_SCORE_40), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test getting the component type.
     */
    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.POINTS, pointsComponent.getComponent());
    }
}
