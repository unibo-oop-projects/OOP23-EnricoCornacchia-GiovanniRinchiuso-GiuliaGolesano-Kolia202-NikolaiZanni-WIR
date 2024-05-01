package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
public class GamePerformanceImpl implements GamePerformance {

    private final GameController gameController;
    private final EntityFactory entityFactory;
    private final Set<Entity> entities = new HashSet<>();
    private final List<Integer> inputs = new ArrayList<>();
    private boolean won;
    private boolean lost;
    private int level;
    private final Random random = new Random();
    /**
     * Constructor for the GamePerformanceImpl.
     * @param gameController the game controller.
     */
    public GamePerformanceImpl(final GameController gameController) {
        this.gameController = gameController;
        this.entityFactory = new EntityFactoryImpl(this);
        this.won = false;
        this.lost = false;
    }
    /**
     * {@inheritDoc}
     */
    public void initialize() {
        initializeEntities();
        this.createGameMap();
    }
    /**
     * Method that create all the element of the map, according to the level.
     */
    private void createGameMap() {
        // TODO Auto-generated method stub
    }
    /**
     * {@inheritDoc}
     */
    public Set<Entity> getEntity() {
        return this.entities;
    }
    /**
     * Read the entities from the controllers and add them to the list of entities. 
     * We will need to add also windows and power ups, but 
     * for now we don't have neither the windows nor the power ups.
     */
    public void initializeEntities() {
        entities.add(this.getRalph());
        entities.add(this.getFelix());
    }
    /**
     * Add bricks or power-ups to the list of entities.
     */
    public void addEntities() {
        entities.addAll(this.getBricks());
    }
    /**
     * {@inheritDoc}
     */
    public void removeEntity(final Entity e) {
        this.entities.remove(e);
    }
    /**
     * {@inheritDoc}
     */
    public void addKey(final int keyCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addKey'");
    }
    /**
     * {@inheritDoc}
     */
    public void removeKey(final int keyCode) {
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
    public void createBrick(final Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBrick'");
    }
    /**
     * {@inheritDoc}
     */
    public void removeBrick(final Pair<Double, Double> pos) {
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
     * @return the position of the power up.
     */
    private Pair<Double, Double> placePowerUps(final EntityType e) {
        boolean alreadyPresent = this.getEntity().stream().anyMatch(ent -> ent.equals(e));
        double x = 0.0, y = 0.0;
        do {
            if (e == EntityType.BIRD) {
                x = Constaints.GameEdges.RIGHT_WALL;
            }
            //if(e == EntityType.CAKE) x must be one of the windows
            switch (level) {
                case 1:
                    y = random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_1);
                    break;
                case 2:
                    y = random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_2);
                    break;
                case 3:
                    y = random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_3);
                    break;
                case 4:
                    y = random.nextDouble(Constaints.PowerUps.BIRD_MAX_Y_4);
                    break;
                default:
                    break;
            }
        } while (!alreadyPresent && y > Constaints.PowerUps.BIRD_MIN_Y);
        return new Pair<>(x, y);
    }
    /**
     * Method that place a bird power up.
     */
    private void placeBird() {
        this.entities.add(this.entityFactory.createBird(placePowerUps(EntityType.BIRD)));
    }

    /**
     * {@inheritDoc}
     */
    public Set<EntityType> powerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'powerUps'");
    }
    /**
     * Getter for the level.
     * @return the level.
     */
    @Override
    public int getLevel() {
        return this.level;
    }
    /**
     * Setter for the level.
     * @param level the level to set.
     */
    public void setLevel(final int level) {
        this.level = level;
    }
    /**
     * Update the game. This has to be the main method of this class, 
     * that delegates the update of the entities to other private methods.
     * When a brick is removed by the brick controller, it will be removed also from the list of entities.
     */
    public void update() {
        // TODO Auto-generated method stub
    }
    /**
     * Returns the set of bricks.
     * @return the set of bricks.
     */
    private Set<Entity> getBricks() {
        return this.gameController.getBrickController().getBricks();
    }
    /**
     * Returns Ralph.
     * @return Ralph.
     */
    private Entity getRalph() {
        return this.gameController.getRalphController().getRalph();
    }
    /**
     * Returns Felix.
     * @return Felix.
     */
    private Entity getFelix() {
        return this.gameController.getFelixController().getFelix();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
    }
}
