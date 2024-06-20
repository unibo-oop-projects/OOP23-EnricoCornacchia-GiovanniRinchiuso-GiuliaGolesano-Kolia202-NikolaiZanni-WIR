package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;

import java.io.IOException;

public final class PointsComponentTest {

    private PointsComponent pointsComponent;

    @SuppressWarnings("static-access")
    @BeforeEach
    void setUp() throws IOException {
        pointsComponent = new PointsComponent();
        pointsComponent.resetHighScoreOnFirstLaunch();
    }

    @Test
    void testReadFromFile() {
        pointsComponent.readFromFile();
        assertEquals(0, pointsComponent.getHighScore(), "High score should be read as 0 from file");
    }

    @Test
    void testGetPoints() {
        assertEquals(0, pointsComponent.getPoints(), "Initial points should be 0");
    }

    @Test
    void testGetHighScore() {
        assertEquals(0, pointsComponent.getHighScore(), "Initial high score should be 0");
    }

    @Test
    void testAddPoints() {
        pointsComponent.addPoints(10);
        assertEquals(10, pointsComponent.getPoints(), "Points should be 10 after adding 10 points");
        assertEquals(10, pointsComponent.getHighScore(), "High score should be updated to 10 after adding 10 points");
        pointsComponent.addPoints(5);
        assertEquals(15, pointsComponent.getPoints(), "Points should be 15 after adding 5 more points");
        assertEquals(15, pointsComponent.getHighScore(),
                "High score should be updated to 15 after adding 5 more points");
    }

    @Test
    void testWriteToFile() throws IOException {
        pointsComponent.writeToFile(Constants.Felix.FIXED_WINDOW_POINTS);
        pointsComponent.readFromFile();
        assertEquals(Constants.Felix.FIXED_WINDOW_POINTS, pointsComponent.getHighScore(),
                "High score should be written as 50 to the file");
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.POINTS, pointsComponent.getComponent());
    }
}
