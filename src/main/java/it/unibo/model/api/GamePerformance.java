package it.unibo.model.api;

import java.util.List;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.utilities.EntityType;

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
     * Method that adds a new entity to the game play.
     * 
     * @param e
     */
    void addEntity(Entity e);

    /**
     * Method that removes a entity from the game play.
     * 
     * @param e
     */
    void removeEntity(Entity e);

    /**
     * Method that adds the new key pressed from keyboard.
     * 
     * @param keyCode
     */
    void addKey(int keyCode);

    /**
     * Method that remove the key released from the keyboard.
     * 
     * @param keyCode
     */
    void removeKey(int keyCode);

    /**
     * Method that returns the list of the keys clicked.
     * 
     * @return the list of inputs
     */
    List<Integer> inputs();

    /**
     * Method that return if the player has lost the game.
     * 
     * @return if Felix has lost.
     */
    boolean isLost();

    /**
     * Method that return if the player has won the game.
     * 
     * @return if Felix has won.
     */
    boolean isWon();

    /**
     * Method that creates a new brick launched.
     * 
     * @param pos
     */
    void createBrick(Pair<Double,Double> pos);

    /**
     * Method that removes tha brick when he got to the bottom of the field.
     * 
     * @param pos
     */
    void removeBrick(Pair<Double,Double> pos);

    /**
     * Method that removes a life from the player’s stocks if a brick hit him.
     */
    void oneLifeLost();

    /**
     * Method that adds one life to the player's stock if he took a power ups.
     */
    void oneLifeEarned();

    /** 
     * Method that manages the spawn of power ups. passare per parametro il tipo di power ups??
     */
    void spawnPowerUps();

    /**
     * Method that returns all the power ups currently present in the game play.
     * 
     * @return a list of entity power ups.
     */
    Set<EntityType> powerUps();
    /**
     * Method that returns the level of the game.
     * 
     * @return the level of the game.
     */
    int getLevel();

    void update();

}