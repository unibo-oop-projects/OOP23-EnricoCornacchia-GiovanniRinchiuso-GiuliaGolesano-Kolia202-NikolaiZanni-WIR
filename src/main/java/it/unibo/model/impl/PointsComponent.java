package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;

/**
 * PointsComponent, it represents the points acquired by an entity.
 */
public class PointsComponent implements Component {
    private int points;
    private int highScore;
    private String filename = "src/main/java/it/unibo/model/impl/scores.txt";

    public PointsComponent() {
        readFromFile();
        this.points = 0;
    }

    public void readFromFile() {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line != null) {
                try {
                    highScore = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println(filename);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println(filename);
        }
    }

    public int getPoints() {
        return this.points;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;

        if (this.points > highScore) {
            highScore = this.points;
            writeToFile(highScore);
        }
    }

    public void writeToFile(int score) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(filename);
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.POINTS;
    }
}