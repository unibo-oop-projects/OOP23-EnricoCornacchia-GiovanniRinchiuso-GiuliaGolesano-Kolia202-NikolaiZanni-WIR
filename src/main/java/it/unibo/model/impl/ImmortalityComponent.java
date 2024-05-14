package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;

public class ImmortalityComponent extends AbstractComponent {
    
    private boolean immortality;
    private long startTime;

    public ImmortalityComponent() {
        this.immortality = false;
    }

    public void setImmortality() {
        this.immortality = true;
        this.startTime = System.currentTimeMillis(); 
    }

    public boolean getImmortality() {
        if (this.immortality) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= 10000) { 
                this.immortality = false; 
            }
        }
        return this.immortality;
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.IMMORTALITY;
    }
}