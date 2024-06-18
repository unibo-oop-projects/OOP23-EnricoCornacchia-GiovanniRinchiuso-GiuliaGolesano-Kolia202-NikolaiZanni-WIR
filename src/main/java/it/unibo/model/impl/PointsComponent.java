package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import it.unibo.model.api.ComponentType;
import it.unibo.view.impl.HighPointsView;
import it.unibo.view.impl.PointsView;

/**
 * PointsComponent represents the points acquired by an entity.
 * This class can be extended to customize point-related functionality.
 */
public class PointsComponent extends AbstractComponent {
    private int points;
    private int highScore;
    private static final String FILENAME = "src/main/java/it/unibo/model/impl/scores.txt";
    private PointsView pointsView;
    private HighPointsView highPointsView;

    /**
     * Constructor for PointsComponent.
     */
    public PointsComponent() {
        this.points = 0;
        this.highScore = 0;
        readFromFile();
    }

    /**
     * Resets the high score to zero and writes it to the file on first launch.
     */
    public static void resetHighScoreOnFirstLaunch() {
        try (FileWriter fileWriter = new FileWriter(FILENAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(Integer.toString(0));
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing file: " + FILENAME);
        }
    }

    /**
     * Reads high score from file.
     */
    public void readFromFile() {
        try (FileReader fileReader = new FileReader(FILENAME);
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
            System.out.println("Error reading file: " + FILENAME);
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

        if (pointsView != null) {
            pointsView.updatePointsLabel();
        }

        if (highPointsView != null) {
            highPointsView.updateHighPointsLabel();
        }
    }

    /**
     * Sets the current points.
     * 
     * @param points the points to set
     */
    public void setPoints(final int points) {
        this.points = points;
        if (pointsView != null) {
            pointsView.updatePointsLabel();
        }
    }

    /**
     * Writes the high score to the file.
     * 
     * @param score the high score to write
     */
    public void writeToFile(final int score) {
        try (FileWriter fileWriter = new FileWriter(FILENAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing file: " + FILENAME);
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


    /**
     * Sets the PointsView associated with this PointsComponent.
     *
     * @param pointsView the PointsView to set
     */
    public void setPointsView(final PointsView pointsView) {
        this.pointsView = pointsView;
    }

    /**
     * Sets the HighPointsView associated with this PointsComponent.
     *
     * @param highPointsView the HighPointsView to set
     */
    public void setHighPointsView(final HighPointsView highPointsView) {
        this.highPointsView = highPointsView;
    }
}
