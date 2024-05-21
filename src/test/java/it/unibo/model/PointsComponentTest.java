package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.PointsComponent;

public class PointsComponentTest {

    PointsComponent pointsComponent;
    private final String filename = "src/main/java/it/unibo/model/impl/scores.txt";

    @BeforeEach
    void setUp() {
        pointsComponent = new PointsComponent();
    }

    @Test
    public void testReadFromFile() {
        pointsComponent.readFromFile();
        assertEquals(0, pointsComponent.getHighScore());
        pointsComponent.addPoints(20);
        pointsComponent.readFromFile();
        assertEquals(20, pointsComponent.getHighScore());
        pointsComponent.addPoints(-10);
        pointsComponent.readFromFile();
        assertEquals(20, pointsComponent.getHighScore());
    }

    @Test
    public void testGetPoints() {
        assertEquals(0, pointsComponent.getPoints());
    }

    @Test
    public void testGetHighScore() {
        assertEquals(0, pointsComponent.getHighScore());
    }

    @Test
    public void testAddPoints() {
        pointsComponent.addPoints(10);
        assertEquals(10, pointsComponent.getPoints());
        pointsComponent.addPoints(5);
        assertEquals(15, pointsComponent.getPoints());
        assertEquals(15, pointsComponent.getHighScore());
        pointsComponent.addPoints(-10);
        assertEquals(5, pointsComponent.getPoints());
        assertEquals(15, pointsComponent.getHighScore());
    }

    @Test
    public void testWriteToFile() {
        pointsComponent.addPoints(20);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename))).trim();
            assertEquals("20", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointsComponent.addPoints(20);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename))).trim();
            assertEquals("40", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointsComponent.addPoints(-10);
        pointsComponent.writeToFile(pointsComponent.getHighScore());
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename))).trim();
            assertEquals("40", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetComponent() {
        assertEquals(ComponentType.POINTS, pointsComponent.getComponent());
    }

}