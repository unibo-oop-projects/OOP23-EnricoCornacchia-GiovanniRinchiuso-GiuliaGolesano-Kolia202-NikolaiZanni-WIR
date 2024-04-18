package it.unibo.controller.api;

import it.unibo.controller.impl.BrickController;
import it.unibo.controller.impl.CollisionManager;
import it.unibo.controller.impl.FelixController;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.InputManager;
import it.unibo.controller.impl.RalphController;
//import it.unibo.core.api;

/**
 * Class that manages controllers.
 */
public interface Application {
    
    /**
     * Getter of the brick controller.
     * 
     * @return the linked brick controller
     */
    BrickController getBrickController();

    /**
     * Getter of the collision manager.
     * 
     * @return the linked collision manager
     */
    CollisionManager getCollisionManager();

    /**
     * Getter of the felix controller.
     * 
     * @return the linked felix controller
     */
    FelixController getFelixController();

    /**
     * Getter of the game controller.
     * 
     * @return the linked game controller
     */
    GameController getGameController();

    /**
     * Getter of the input manager.
     * 
     * @return the linked input manager
     */
    InputManager getInputManager();

    /**
     * Getter of the ralph controller.
     * 
     * @return the linked ralph controller
     */
    RalphController getRalphController();

    /**
     * Getter of the Game Engine.
     * 
     * @return the linked Game Engine
     */
    //GameEngine getGameEngine();
}
