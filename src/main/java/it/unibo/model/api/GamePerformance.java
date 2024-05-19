package it.unibo.model.api;

import java.util.List;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.utilities.EntityType;
import javafx.scene.input.KeyCode;

/**
 * Interface that manages the game performance with all actions and elements present in the game play. 
 */
public interface GamePerformance {
    /**
     * Method that initialize the game.
     */
    void initialize();

    /**
     * Getter for the list of entities.
     * 
     * @return a list of entities present.
     */
    Set<Entity> getEntity();

    /**
     * Getter for the list of input.
     * 
     * @return a list of inputs.
     */
    List<KeyCode> getInputs();

    /**
     * Method that adds a new entity to the game play.
     * 
     * @param e the entity to add.
     */
    void addEntity(Entity e);

    /**
     * Method that removes a entity from the game play.
     * 
     * @param e the entity to remove.
     */
    void removeEntity(Entity e);

    /**
     * Method that adds the new key pressed from keyboard.
     * 
     * @param e the key pressed
     */
    void addKey(KeyCode e);

    /**
     * Method that removes tha brick when he got to the bottom of the field.
     * 
     * @param pos the position of the brick.
     */
    void removeBrick(Pair<Double, Double> pos);

    /** 
     * Method that manages the spawn of power ups. passare per parametro il tipo di power ups??
     */
    void spawnPowerUps();

    /**
     * Get all the power ups currently present in the game.
     * @return
     */
    List<Entity> getPowerUpsPresent();

    /**
     * Get all the brick currently present in the game.
     * @return
     */
    List<Entity> getBrickPresent();

    /**
     * Get all the windows in the entities list.
     * @return
     */
    List<Entity> getWindows();

    /**
     * Get the level.
     * 
     * @return the game controller.
     */
    int getLevel();
}
