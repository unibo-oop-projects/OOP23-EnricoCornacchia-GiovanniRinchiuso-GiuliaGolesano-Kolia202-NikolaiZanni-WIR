package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.EntityType;
import javafx.scene.input.KeyCode;

/**
 * Class that manages the game performance of the play.
 */
public class GamePerformanceImpl implements GamePerformance {

    private final GameController gameController;
    private final EntityFactory entityFactory;
    private final Set<Entity> entities = new HashSet<>();
    private final List<KeyCode> inputs = new ArrayList<>();
    private final Random random = new Random();
    /**
     * Constructor for the GamePerformanceImpl.
     * @param gameController the game controller.
     */
    public GamePerformanceImpl(final GameController gameController) {
        this.gameController = gameController;
        this.entityFactory = new EntityFactoryImpl(this);
    }
    /**
     * {@inheritDoc}
     */
    public void initialize() {
        this.initializeEntities();
        this.createGameMap();
        this.spawnPowerUps();
    }
    /**
     * Read the entities from the controllers and add them to the list of entities. 
     * We will need to add also windows and power ups, but 
     * right now we don't have them.
     */
    public void initializeEntities() {
        entities.add(this.gameController.getRalphController().getRalph());
        entities.add(this.gameController.getFelixController().getFelix());
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
     * {@inheritDoc}
     */
    @Override
    public List<KeyCode> getInputs(){
        return this.inputs;
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
    public void addKey(final KeyCode keyCode) {
        this.inputs.add(keyCode);
        //System.out.print("Keycode added\n");
    }
    /**
     * {@inheritDoc}
     */
    public void removeBrick(final Pair<Double, Double> pos) {
        entities.remove(entities.stream()
                               .filter(e -> e.getEntityType() == EntityType.BRICK && e.getPosition().equals(pos))
                               .findFirst()
                               .orElse(null));
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
            y = random.nextDouble(Constaints.PowerUps.BIRD_MIN_Y);
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
    @Override
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getPowerUpsPresent() {
        return this.entities.stream()
                                .filter(entity -> entity.getEntityType() == EntityType.BIRD 
                                                    || entity.getEntityType() == EntityType.CAKE).toList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getBrickPresent() {
        return this.entities.stream()
                                .filter(entity -> entity.getEntityType() == EntityType.BRICK).toList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getWindows() {
        return this.entities.stream()
                                .filter(entity -> entity.getEntityType() == EntityType.WINDOW).toList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevel(){
        return this.gameController.getLevel();
    }
}
