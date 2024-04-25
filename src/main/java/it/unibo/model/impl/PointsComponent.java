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
        this.points = 9999999;
    }

    /**
     * Method to add points.
     */
    public void addPoints(final int pointsToAdd) {
        this.points += pointsToAdd;
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

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

