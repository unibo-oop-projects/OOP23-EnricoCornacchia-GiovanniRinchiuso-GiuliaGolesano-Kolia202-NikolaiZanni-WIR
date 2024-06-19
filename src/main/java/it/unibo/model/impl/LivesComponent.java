package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.api.ComponentType;
import it.unibo.utilities.GameState;

/**
 * LivesComponent, it represents the lives of the entity.
 */
public class LivesComponent extends AbstractComponent {

    private int lives;
    private boolean immortality;
    private final List<LivesChangeListener> listeners = new ArrayList<>();

    public interface LivesChangeListener {
        void onLivesChanged(int newLives);
    }

    public void addLivesChangeListener(LivesChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyLivesChange() {
        for (LivesChangeListener listener : listeners) {
            listener.onLivesChanged(this.lives);
        }
    }

    public LivesComponent() {
        this.lives = 3;
        immortality = false;
    }

    public void stealLives() {
        if (!immortality && getLives() > 0) {
            lives--;
            notifyLivesChange();
            if (getLives() == 0) {
                GameState.setGameState(GameState.GAMEOVER);
            }
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void setImmortality() {
        this.immortality = true;
    }

    public boolean isImmortality() {
        return this.immortality;
    }

    public void setStopImmortality() {
        this.immortality = false;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.LIFE;
    }
}