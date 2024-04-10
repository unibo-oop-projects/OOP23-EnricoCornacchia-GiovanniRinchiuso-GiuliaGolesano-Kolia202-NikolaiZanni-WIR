package it.unibo.controller;
import java.util.Set;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constaints;

public class BrickController {

    private Set<Entity> bricks;

    public Set<Entity> getBricks() {
        return this.bricks;
    }

    public void fallBricks() {
        this.checkBricks();
        for (Entity brick : bricks) {
            for(Component component : brick.getComponents()) {
                if(component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent)component).move(Constaints.BRICK_SPEED, 0.0, brick);
                }
            }
        }
    }

    private void checkBricks() {
        for (Entity brick : bricks) {
            for(Component component : brick.getComponents()) {
                if(component.getComponent() == ComponentType.MOVEMENT) {
                    if(!((MovementComponent)component).canMove(Constaints.BRICK_SPEED, 0.0, brick)) {
                        bricks.remove(brick);
                    }
                }
            }
        }
    }


}
