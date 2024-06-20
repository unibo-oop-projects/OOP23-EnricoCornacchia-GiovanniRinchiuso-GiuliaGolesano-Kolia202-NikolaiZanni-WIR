package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.core.impl.GameEngineImpl;
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
    private static final String FILENAME = "src/main/java/it/unibo/model/impl/Scores.txt";
    private final Set<PointsView> pointsViews = new HashSet<>();
    private final Set<HighPointsView> highPointsViews = new HashSet<>();
    private static final Logger LOGGER = Logger.getLogger(GameEngineImpl.class.getName());

    /**
     * Constructor for PointsComponent.
     */
    public PointsComponent() {
        this.points = 0;
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
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in resetHighScoreOnFirstLaunch", e);
        }
    }

    /**
     * Reads high score from file.
     */
    public final void readFromFile() {
        try (FileReader fileReader = new FileReader(FILENAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            final String line = bufferedReader.readLine();
            if (line != null) {
                try {
                    highScore = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.SEVERE, "Error in readFromFile", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in readFromFile fileReader", e);
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

        pointsViews.forEach(view -> {
            view.updatePointsLabel();
        });
        highPointsViews.forEach(view -> {
            view.updateHighPointsLabel();
        });
    }

    /**
     * Sets the current points.
     * 
     * @param points the points to set
     */
    public void setPoints(final int points) {
        this.points = points;
        pointsViews.forEach(PointsView::updatePointsLabel);
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
            LOGGER.log(Level.SEVERE, "Error in writeToFile", ex);
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
     * Adds a PointsView to be notified when points change.
     *
     * @param pointsView the PointsView to add
     */
    public void addPointsView(final PointsView pointsView) {
        this.pointsViews.add(pointsView);
    }

    /**
     * Adds a HighPointsView to be notified when high points change.
     *
     * @param highPointsView the HighPointsView to add
     */
    public void addHighPointsView(final HighPointsView highPointsView) {
        this.highPointsViews.add(highPointsView);
    }
}
