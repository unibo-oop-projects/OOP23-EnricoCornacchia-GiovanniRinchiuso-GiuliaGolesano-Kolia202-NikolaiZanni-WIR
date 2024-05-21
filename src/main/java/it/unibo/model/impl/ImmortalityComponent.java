package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;

public class ImmortalityComponent extends AbstractComponent {
    
    private long startTime;

    public void setImmortality(LivesComponent livesComponent) {
        livesComponent.setImmortality();
        this.startTime = System.currentTimeMillis(); 
    }

    public void chekStopImmortality(LivesComponent livesComponent) {
        if (livesComponent.isImmortality()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= 10000) { 
                livesComponent.setStopImmortality(); 
            }
        }
    }

    @Override
    public ComponentType getComponent() {
        return ComponentType.IMMORTALITY;
    }
}