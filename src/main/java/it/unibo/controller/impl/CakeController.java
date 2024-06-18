package it.unibo.controller.impl;

import java.util.HashSet;
import java.util.Set;
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;

/**
 * The CakeController class is responsible for managing the creation and removal of cakes in the game.
 */
public class CakeController {
    private static final long CREATION_INTERVAL = 11000;
    private static final long ACTIVE_DURATION = 5000;

    private long lastCreationTime = 0;
    private final GamePerformance gamePerformance;
    private final Set<Entity> activeCakes = new HashSet<>();
    private final Set<Pair<Entity, Long>> cakesCreationTimes = new HashSet<>();

    /**
     * Creates a new CakeController.
     *
     * @param gamePerformance the game performance
     */
    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Returns the set of active cakes.
     *
     * @return the set of active cakes
     */
    public Set<Entity> getCakes() {
        return this.activeCakes;
    }

    private void createCake() {
        CakePositionComponent cakePositionComponent = new CakePositionComponent();
        Pair<Double, Double> position = cakePositionComponent.randomPosition();
        Entity cake = new EntityFactoryImpl(this.gamePerformance).createCake(position);
        this.gamePerformance.addEntity(cake);
        this.activeCakes.add(cake);
        this.cakesCreationTimes.add(new Pair<>(cake, System.currentTimeMillis()));
    }

    private void removeCake(final Entity cake) {
        this.gamePerformance.removeEntity(cake);
        this.activeCakes.remove(cake);
        this.cakesCreationTimes.removeIf(pair -> pair.getX().equals(cake));
    }

    /**
     * Updates the state of the cake controller.
     * This method checks if it's time to create a new cake and removes cakes that have exceeded their active duration.
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCreationTime >= CREATION_INTERVAL) {
            createCake();
            lastCreationTime = currentTime;
        }
        Set<Entity> cakesToRemove = new HashSet<>();
        for (Pair<Entity, Long> pair : cakesCreationTimes) {
            if (currentTime - pair.getY() >= ACTIVE_DURATION) {
                cakesToRemove.add(pair.getX());
            }
        }
        for (Entity cake : cakesToRemove) {
            removeCake(cake);
        }
    }
}
