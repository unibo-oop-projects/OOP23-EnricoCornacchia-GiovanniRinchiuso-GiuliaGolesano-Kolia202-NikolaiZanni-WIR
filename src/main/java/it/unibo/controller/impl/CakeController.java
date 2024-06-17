package it.unibo.controller.impl;

import java.util.HashSet;
import java.util.Set;
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;

 public class CakeController {
    private static final long CREATION_INTERVAL = 11000;
    private static final long ACTIVE_DURATION = 5000;

    private long lastCreationTime = 0;
    private final GamePerformance gamePerformance;
    private final Set<Entity> activeCakes = new HashSet<>();
    private final Set<Pair<Entity, Long>> cakesCreationTimes = new HashSet<>();

    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

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

    private void removeCake(Entity cake) {
        this.gamePerformance.removeEntity(cake);
        this.activeCakes.remove(cake);
        this.cakesCreationTimes.removeIf(pair -> pair.getX().equals(cake));
    }

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