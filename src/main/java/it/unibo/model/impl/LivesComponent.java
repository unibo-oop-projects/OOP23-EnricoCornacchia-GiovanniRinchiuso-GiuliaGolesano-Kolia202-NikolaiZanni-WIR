package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.GameState;

/**
 * LivesComponent, it represents the lives of the entity.
 */
public class LivesComponent extends AbstractComponent {

    private int lives;
    private boolean immortality;

    /**
     * Initialize lives to 3.
     * @param gamePerformance.
     */
    public LivesComponent(final GamePerformance gamePerformance) {
        this.lives = 3;
        immortality = false;
    }

    /**
     * Method of stealing a life.
     */
    public void stealLives() {
        if (!immortality && getLives() > 0) {
            lives--;
            if (getLives() == 0) {
                GameState.setGameState(GameState.GAMEOVER);
            }
        }
    }
    /*
     * Method to return the number of lifes.
     */
    public int getLives() {
        return this.lives;
    }
    /*
     * Method to set the immortality.
     */
    public void setImmortality() {
        this.immortality = true;
    }
    /*
     * Method to return the immortality variable.
     */
    public boolean isImmortality() {
        return this.immortality;
    }
    /**
     * Method to stop the immortality.
     */
    public void setStopImmortality() {
        this.immortality = false;
    }
    @Override
    public ComponentType getComponent() {
        return ComponentType.LIFE;
    }
}
