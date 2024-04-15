package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.utilities.EntityType;
import it.unibo.model.api.Component;

/**
 * EntityFactoryImpl.
 */
public class EntityFactoryImpl implements EntityFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createFelix(final Pair<Double, Double> pos) {
        Set<Component> components = new HashSet<Component>(Arrays.asList(new MovementComponent(),
                new PointsComponent(),
                new LivesComponent(),
                new FixWindowsComponent(),
                new HitboxComponent()));
        Entity felix = new EntityImpl(EntityType.FELIX, pos, components);
        return felix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        Set<Component> components = new HashSet<Component>(Arrays.asList(new MovementComponent(),
                new HitboxComponent()));
        Entity ralph = new EntityImpl(EntityType.RALPH, pos, components);
        return ralph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> createWindows(final Set<Pair<Double, Double>> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWindows'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBrick(final Pair<Double, Double> pos) {
        Set<Component> components = new HashSet<Component>(Arrays.asList(new MovementComponent()));
        Entity brick = new EntityImpl(EntityType.BRICK, pos, components);
        return brick;
    }
}
