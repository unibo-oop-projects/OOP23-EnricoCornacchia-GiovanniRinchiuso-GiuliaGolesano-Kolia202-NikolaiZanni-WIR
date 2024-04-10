package it.unibo.model.impl;

import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;

/**
 * EntityFactoryImpl.
 */
public class EntityFactoryImpl implements EntityFactory {
    /**
     * Create a new Felix.
     * @param pos the position of the new Felix.
     * @return the new Felix.
     */
    @Override
    public Entity createFelix(final Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFelix'");
    }
    /**
     * Create a new Ralph.
     * @param pos the position of the new Ralph.
     * @return the new Ralph.
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRalph'");
    }
    /**
     * Create all the Windows.
     * @param pos the positions of the windows.
     * @return the created windows set.
     */
    @Override
    public Set<Entity> createWindows(final Set<Pair<Double, Double>> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWindows'");
    }
    /**
     * Create a new brick.
     * @param pos the position of the new brick.
     * @return the new brick.
     */
    @Override
    public Entity createBrick(final Pair<Double, Double> pos) {
        throw new UnsupportedOperationException("Unimplemented method 'createBrick'");
    }
}