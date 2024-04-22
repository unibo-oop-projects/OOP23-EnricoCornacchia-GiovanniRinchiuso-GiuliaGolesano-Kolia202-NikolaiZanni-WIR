package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.EntityType;

/**
 * Class that manages the game performance of the play.
 */
public class GamePerformanceImpl implements GamePerformance{

    private final GameController gameController;
    private final EntityFactory entityFactory;
    private final List<EntityType> entities = new ArrayList<>();
    private final List<Integer> inputs = new ArrayList<>();
    private boolean won;
    private boolean lost;
    
    public GamePerformanceImpl(final GameController gameController){
        this.gameController = gameController;
        this.entityFactory = new EntityFactoryImpl();
        this.won = false;
        this.lost = false;
    }

    /**
     * {@inheritDoc}
     */
    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    /**
     * {@inheritDoc}
     */
    public List<Entity> getEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntity'");
    }

    /**
     * {@inheritDoc}
     */
    public void addEntity(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
    }

    /**
     * {@inheritDoc}
     */
    public void removeEntity(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEntity'");
    }

    /**
     * {@inheritDoc}
     */
    public void addKey(int KeyCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addKey'");
    }

    /**
     * {@inheritDoc}
     */
    public void removeKey(int KeyCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeKey'");
    }

    /**
     * {@inheritDoc}
     */
    public List<Integer> inputs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputs'");
    }

    /**
     * {@inheritDoc}
     */
    public boolean gameLost() {
        return this.lost;
    }

    /**
     * {@inheritDoc}
     */
    public boolean gameWon() {
        return this.won;
    }

    /**
     * {@inheritDoc}
     */
    public void createBrick(Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBrick'");
    }

    /**
     * {@inheritDoc}
     */
    public void removeBrick(Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBrick'");
    }

    /**
     * {@inheritDoc}
     */
    public void oneLifeLost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oneLifeLost'");
    }

    /**
     * {@inheritDoc}
     */
    public void oneLifeEarned() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oneLifeEarned'");
    }

    /**
     * {@inheritDoc}
     */
    public void spawnPowerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'spawnPowerUps'");
    }

    /**
     * {@inheritDoc}
     */
    public List<EntityType> powerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'powerUps'");
    }
    
}
