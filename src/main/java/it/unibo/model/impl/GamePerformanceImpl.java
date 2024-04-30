package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.EntityType;

/**
 * Class that manages the game performance of the play.
 */
public class GamePerformanceImpl implements GamePerformance{

    private final GameController gameController;
    private final EntityFactory entityFactory;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Integer> inputs = new ArrayList<>();
    private boolean won;
    private boolean lost;
    private int level;
    private final Random random = new Random();
    
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
        this.createGameMap();
    }
    /** 
     * Method to place the player felix, same position in every level. 
     */
    private void placeFelix() {
        this.entities.add(this.entityFactory.createFelix(Constaints.Felix.FELIX_START));
    }

    /**
     * Method that create all the element of the map, according to the level.
     */
    private void createGameMap(){

    }


    /**
     * {@inheritDoc}
     */
    public List<Entity> getEntity() {
        return this.entities;
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
    public boolean isLost() {
        return this.lost;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isWon() {
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
    }

    /**
     * Method that generate a random position for the power ups.
     * @param e
     * @return
     */
    private Pair<Double,Double> placePowerUps(final EntityType e){
        boolean alreadyPresent = this.getEntity().stream().anyMatch(ent -> ent.equals(e));
        double x = 0.0, y = 0.0;
        do{
            if(e == EntityType.BIRD) x = Constaints.gameEdges.RIGHT_WALL;
            //if(e == EntityType.CAKE) x must be one of the windows
            switch (level) {
                case 1:
                    y=random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_1);
                    break;
                case 2:
                    y=random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_2);
                    break;
                case 3:
                    y=random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_3);
                    break;
                case 4:
                    y=random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_4);
                    break;
                default:
                    break;
            }
        }while(!alreadyPresent && y>Constaints.PowerUps.BIRD_MIN_Y);
        return new Pair<>(x, y);
    }

    /**
     * Method that place a bird power up.
     */
    private void placeBird(){
        this.entities.add(this.entityFactory.createBird(placePowerUps(EntityType.BIRD)));
    }

    /**
     * {@inheritDoc}
     */
    public List<EntityType> powerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'powerUps'");
    }
    @Override
    /**
     * Getter for the level.
     * @return the level.
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * Setter for the level.
     * @param level the level to set.
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * Update the game.
     */
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
