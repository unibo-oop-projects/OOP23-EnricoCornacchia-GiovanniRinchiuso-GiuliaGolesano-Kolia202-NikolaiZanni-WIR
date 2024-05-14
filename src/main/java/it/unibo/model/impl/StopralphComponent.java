package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;

public class StopRalphComponent extends AbstractComponent{

    private boolean blocked;
    private long startTime;

    public StopRalphComponent() {
        this.blocked = false;
    }

    public void setStopralph() {
        this.blocked = true;
        this.startTime = System.currentTimeMillis();
    }

    public boolean getStopRalph() {
        if (this.blocked) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= 10000) {
                this.blocked = false;
            }
        }
        return this.blocked;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.STOPRALPH;
    }

}