package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import it.unibo.model.api.ComponentType;

/**
 * PointsComponent represents the points acquired by an entity.
 * This class can be extended to customize point-related functionality.
 */
public class PointsComponent extends AbstractComponent {
    private int points;
    private int highScore;
    private final String filename = "src/main/java/it/unibo/model/impl/scores.txt";

    /**
     * Constructor for PointsComponent.
     */
    public PointsComponent() {
        this.points = 0;
        this.highScore = 0;
        writeToFile(0);
    }

    /**
     * Reads high score from file.
     */
    public void readFromFile() {
        try (FileReader fileReader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            if (line != null) {
                try {
                    highScore = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing high score from file");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filename);
        }
    }

    /**
     * Gets the current points.
     * 
     * @return the current points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Gets the high score.
     * 
     * @return the high score
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     * Adds points to the current total.
     * 
     * @param pointsToAdd the points to add
     */
    public void addPoints(final int pointsToAdd) {
        this.points += pointsToAdd;

        if (this.points > highScore) {
            highScore = this.points;
            writeToFile(highScore);
        }
    }

    /**
     * Writes the high score to the file.
     * 
     * @param score the high score to write
     */
    public void writeToFile(final int score) {
        try (FileWriter fileWriter = new FileWriter(filename);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing file: " + filename);
        }
    }

    /**
     * Gets the component type associated with points.
     * 
     * @return the component type for points
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.POINTS;
    }
}
