package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.EntityType;
import it.unibo.model.api.Component;

/**
 * EntityFactoryImpl.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final GamePerformance gamePerformance;

    /**
     * EntityFactoryImpl constructor.
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
                new LivesComponent(),
                new FixWindowsComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.FELIX)));
        return new EntityImpl(EntityType.FELIX, pos, gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new ThrowBrickComponent(gamePerformance),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.RALPH)));
        return new EntityImpl(EntityType.RALPH, pos, gamePerformance, components);
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
        return new EntityImpl(EntityType.BRICK, pos, gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createCake(final Pair<Double, Double> pos) {
        Random rand = new Random();
        double cakeX = rand.nextDouble() * (Constaints.PowerUps.CAKE_MAX_X - Constaints.PowerUps.CAKE_MIN_X) + Constaints.PowerUps.CAKE_MIN_X;
        double cakeY;
        switch (rand.nextInt(3)) {
            case 0:
            cakeY = Constaints.PowerUps.CAKE_FLOOR_1_Y;
            break;
            case 1:
            cakeY = Constaints.PowerUps.CAKE_FLOOR_2_Y;
            break;
            default:
            cakeY = Constaints.PowerUps.CAKE_FLOOR_3_Y;
            break;
        }
        final Pair<Double, Double> randomPos = new Pair<>(cakeX, cakeY);
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        new ImmortalityComponent();
        new LivesComponent();
        return new EntityImpl(EntityType.CAKE, randomPos, gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBird(final Pair<Double, Double> pos) {
        Random rand = new Random();
        double birdX = rand.nextDouble() * (Constaints.PowerUps.BIRD_MAX_X - Constaints.PowerUps.BIRD_MIN_x) + Constaints.PowerUps.BIRD_MIN_x;
        double birdY = rand.nextDouble() * (Constaints.PowerUps.BIRD_MAX_Y - Constaints.PowerUps.BIRD_MIN_Y) + Constaints.PowerUps.BIRD_MIN_Y;
        final Pair<Double, Double> randomPos = new Pair<>(birdX, birdY);
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent()));
        new StopRalphComponent();
        new MovementComponent();
        return new EntityImpl(EntityType.BIRD, randomPos, gamePerformance, components);
    }
}
