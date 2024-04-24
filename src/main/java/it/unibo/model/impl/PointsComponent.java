package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.view.impl.PointsView;

/**
 * PointsComponent, it represents the points acquired by an entity.
 */
public class PointsComponent implements Component {

    private int points;
    private PointsView pointsView;

    /*
     * Initialize points to 0.
     */
    public PointsComponent() {
        this.points = 10000;
    }

    /**
     * Method to add points.
     */
    public void addPoints(final int pointsToAdd) {
        this.points += pointsToAdd;
        if (pointsView != null) {
            pointsView.updatePointsLabel();
        }
    }

    /**
     * Method to get the current points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Method to set the PointsView.
     */
    public void setPointsView(PointsView pointsView) {
        this.pointsView = pointsView;
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
