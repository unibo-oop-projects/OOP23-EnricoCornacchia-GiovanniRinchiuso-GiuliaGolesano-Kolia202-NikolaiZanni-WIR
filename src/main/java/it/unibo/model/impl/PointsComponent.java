package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;

/**
 * PointsComponent, it represents the points acquired by an entity.
 */
public class PointsComponent implements Component {

    private int points;

    /*
     * Initialize points to 0.
     */
    public PointsComponent() {
        this.points = 0;
    }

    /**
     * Method to add ponits.
     */
    public void addPoints(final int pointsToAdd) {
        this.points += pointsToAdd;
    }

    /**
     * Method of stealing points
     */
    public void stealPoint(final int pointsToSteal) {
        this.points -= pointsToSteal;
    }

    /**
     * Method to get the current points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * getter of the type of the class.
     * 
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.POINTS;
    }

}
