package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;

public class ImmortalityComponent extends AbstractComponent {
    final int STARTTIME = 10000;
    private long startTime;
    /*
     * Method to set the immortality.
     */
    public void setImmortality(LivesComponent livesComponent) {
        livesComponent.setImmortality();
        this.startTime = System.currentTimeMillis(); 
    }
    /*
     * Method to check if the immortality is stopped.
     */
    public void chekStopImmortality(LivesComponent livesComponent) {
        if (livesComponent.isImmortality()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= STARTTIME) { 
                livesComponent.setStopImmortality(); 
            }
        }
    }
    @Override
    public ComponentType getComponent() {
        return ComponentType.IMMORTALITY;
    }
}