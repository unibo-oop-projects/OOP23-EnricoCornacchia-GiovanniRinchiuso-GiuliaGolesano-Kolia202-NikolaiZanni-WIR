package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;

public class StopralphComponent implements Component {

    private boolean stopRalph;
    private long startTime;

    public StopralphComponent() {
        this.stopRalph = false;
    }

    public void setStopralph() {
        this.stopRalph = true;
        this.startTime = System.currentTimeMillis();
    }

    public boolean getStopRalph() {
        if (this.stopRalph) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= 10000) {
                this.stopRalph = false;
            }
        }
        return this.stopRalph;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.STOPRALPH;
    }

}
