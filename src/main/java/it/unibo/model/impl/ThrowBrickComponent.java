package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;

import java.util.Set;
import it.unibo.common.Pair;

/**
 * Component for the brick throwing.
 */
public class ThrowBrickComponent extends AbstractComponent {

        private StopRalphComponent stopRalphComponent;
        private final EntityFactoryImpl entityFactoryImpl;
        private final GamePerformance gamePerformance;
        /**
         * Constructor for the ThrowBrickComponent.
         * @param gamePerformance the game performance.
         */
        public ThrowBrickComponent(final GamePerformance gamePerformance) {
            this.gamePerformance = gamePerformance;
            this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
            this.stopRalphComponent = new StopRalphComponent();
        }
        /**
         * Create a new brick in the position passed as parameter and add it to the set of bricks.
         * @param bricks the set of bricks.
         * @param position  the position where the brick will be created.
         */
        public void addBrickToThrow(final Set<Entity> bricks, final Pair<Double, Double> position) {
            if (!stopRalphComponent.getStopRalph()) {
                bricks.add(entityFactoryImpl.createBrick(position));
            }
        }
        /**
         * Getter for the component type.
         * @return the component type.
         */
        @Override
        public ComponentType getComponent() {
            return ComponentType.THROWBRICK;
        }
}
