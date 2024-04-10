package it.unibo.model.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import java.util.Set;
import it.unibo.common.Pair;

/**
 * Component for the brick throwing.
 */
public class ThrowBrickComponent implements Component {

        private boolean blocked;
        private EntityFactoryImpl entityFactoryImpl;

        /**
         * Constructor for the ThrowBrickComponent.
         */
        public ThrowBrickComponent() {
            this.blocked = false;
            this.entityFactoryImpl = new EntityFactoryImpl();
        }

        /**
         * Create a new brick in the position passed as parameter and add it to the set of bricks.
         * @param bricks the set of bricks.
         * @param position  the position where the brick will be created.
         */
        public void addBrickToThrow(Set<Entity> bricks, Pair<Double, Double> position) {
            if (!blocked){
                bricks.add(entityFactoryImpl.createBrick(position));
            }
            
        }
    
        public ComponentType getComponent() {
            return ComponentType.THROWBRICK;
        }

        public void block() {
            this.blocked = true;
        }

        public void unblock() {
            this.blocked = false;
        }
    
}
