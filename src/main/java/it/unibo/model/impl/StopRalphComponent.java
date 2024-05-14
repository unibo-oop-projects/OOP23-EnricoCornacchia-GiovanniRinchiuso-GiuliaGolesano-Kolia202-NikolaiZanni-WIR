package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;


public class StopRalphComponent extends AbstractComponent{

    private long startTime;

    public void setStopRalph(ThrowBrickComponent throwBrickComponent) {
        throwBrickComponent.setBlocked();
        this.startTime = System.currentTimeMillis();
    }

    public void checkUnlockRalph(ThrowBrickComponent throwBrickComponent) {
        if (throwBrickComponent.isBlocked()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= 10000) {
                throwBrickComponent.setUnblocked();
            }
        }
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.STOPRALPH;
    }

}