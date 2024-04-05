package it.unibo.model.api;

import it.unibo.common.Pair;

import java.util.Set;

/**
 * EntityFactory
 */
public interface EntityFactory {

    /**
     * Create Felix.
     * 
     * @param pos starting Felix's position.
     * @return the created Felix entity.
     */
    public Entity createFelix(final Pair<Double, Double> pos);

    /**
     * Create Ralph.
     * 
     * @param pos starting Ralph's position.
     * @return the created Ralph entity.
     */
    public Entity createRalph(final Pair<Double, Double> pos);


    /**
     * Create the windows.
     * @param pos starting windows's position.
     * @return the created windows entity.
     */
    public Entity createWindows(final Set <Pair<Double, Double>> pos);

    /**
     * Create the brick.
     * @param pos starting brick's position.
     * @return the created brick entity.
     */

    public Brick createBrick(final Pair<Double, Double> pos);

}