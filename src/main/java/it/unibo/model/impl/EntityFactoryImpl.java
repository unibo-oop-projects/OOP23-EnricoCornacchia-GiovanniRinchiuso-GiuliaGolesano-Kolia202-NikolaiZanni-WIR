package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.EntityType;
import it.unibo.model.api.Component;

/**
 * EntityFactoryImpl.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final GamePerformance gamePerformance;

    /**
     * EntityFactoryImpl constructor.
     * 
     * @param gamePerformance the game performance of the entity factory.
     */
    public EntityFactoryImpl(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createFelix(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new PointsComponent(),
                new LivesComponent(this.gamePerformance),
                new FixWindowsComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.FELIX)));
        return new EntityImpl(EntityType.FELIX, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new ThrowBrickComponent(this.gamePerformance),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.RALPH)));
        return new EntityImpl(EntityType.RALPH, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWindows(final Pair<Double, Double> pos, final boolean state) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new FixedWindowsComponent(state)));
        return new EntityImpl(EntityType.WINDOW, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBrick(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        return new EntityImpl(EntityType.BRICK, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createCake(final Pair<Double, Double> pos) {
        CakePositionComponent cakePositionComponent = new CakePositionComponent();
        Pair<Double, Double> cakePosition = cakePositionComponent.randomPosition();
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        components.add(cakePositionComponent);
        return new EntityImpl(EntityType.CAKE, cakePosition, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBird(final Pair<Double, Double> pos) {
        BirdPositionComponent birdPositionComponent = new BirdPositionComponent();
        Pair<Double, Double> birdPosition = birdPositionComponent.randomPosition();
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        components.add(birdPositionComponent);
        new MovementComponent();
        return new EntityImpl(EntityType.BIRD, birdPosition, this.gamePerformance, components);
    }
}