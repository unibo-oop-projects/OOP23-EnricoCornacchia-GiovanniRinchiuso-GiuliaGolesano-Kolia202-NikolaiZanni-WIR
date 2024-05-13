package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;

/**
 * LivesComponent, it represents the lives of the entity.
 */
public class LivesComponent implements Component {

    private int lives;
    private ImmortalityComponent immortalityComponent;
    /**
     * Initialize lives to 3.
     */
    public LivesComponent() {
        this.lives = 3;
        this.immortalityComponent = new ImmortalityComponent();
    }

    /**
     * Method of stealing a life.
     */
    public void stealLives() {
        if(!immortalityComponent.getImmortality()) {
        this.lives = this.lives - 1;
        }
    }
    /**
     * Method to get the current lives.
     * 
     * @return the current numer of lives.
     */
    public final int getLives() {
        return this.lives;
    }

    /**
     * getter of the type of the class.
     * 
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.LIFE;
    }
}
