package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.view.api.Listener;

/**
 * PointsComponent, it represents the points acquired by an entity.
 */
public class PointsComponent implements Component {
    private int points;
    private int highScore;
    private String filename = "src/main/java/it/unibo/model/impl/scores.txt";
    private List<Listener> pointsChangeListeners = new ArrayList<>();
    private static PointsComponent score = new PointsComponent();


    public PointsComponent() {
        readFromFile();
        points = 0;
    }
    
    public static PointsComponent getScore() {
		return score;
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
                    System.out.println("Formato non valido nel file '" + filename);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Errore durante la lettura del file '" + filename + "'");
        }
    }
    
    public int getPoints(){
		return points;
	}

    
    public int getHighScore() {
        return highScore;
    }


    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;

        if (this.points > highScore) {
            highScore = this.points;
            writeToFile(highScore);
        }
        notifyPointsChanged(this.points);
    }

    public void writeToFile(int score) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + filename + "'");
        }
    }

    public void registerPointsChangeListener(Listener listener) {
        pointsChangeListeners.add(listener);
    }

    public void removePointsChangeListener(Listener listener) {
        pointsChangeListeners.remove(listener);
    }

    private void notifyPointsChanged(int newPoints) {
        for (Listener listener : pointsChangeListeners) {
            listener.onPointsChanged(newPoints);
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