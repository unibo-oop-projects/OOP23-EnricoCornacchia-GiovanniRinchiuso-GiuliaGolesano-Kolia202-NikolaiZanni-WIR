package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constaints.Felix;
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
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new PointsComponent(),
                new LivesComponent(),
                new FixWindowsComponent(),
                new HitboxComponent(Felix.FELIX_START.getX(), Felix.FELIX_START.getY(), EntityType.FELIX)));
        return new EntityImpl(EntityType.FELIX, pos, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.RALPH)));
        return new EntityImpl(EntityType.RALPH, pos, components);
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
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        return new EntityImpl(EntityType.BRICK, pos, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createCake(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        return new EntityImpl(EntityType.CAKE, pos, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBird(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        return new EntityImpl(EntityType.BIRD, pos, components);
    }
}
