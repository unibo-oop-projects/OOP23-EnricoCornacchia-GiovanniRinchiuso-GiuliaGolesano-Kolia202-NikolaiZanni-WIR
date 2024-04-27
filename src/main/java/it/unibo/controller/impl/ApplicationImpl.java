package it.unibo.controller.impl;

import it.unibo.controller.api.Application;
import it.unibo.core.api.GameEngine;
import it.unibo.core.impl.GameEngineImpl;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;

public class ApplicationImpl implements Application {

    private BrickController brickController;
    private CollisionManager collisionManager;
    private FelixController felixController;
    private GameController gameController;
    private InputManager inputManager;
    private RalphController ralphController;
    private GamePerformance gamePerformance;
    private GameEngine gameEngine;

    /**
     * Constructor.
     */
    public ApplicationImpl() {
        setController();
        this.gamePerformance = new GamePerformanceImpl(this.gameController);
        this.gameEngine = new GameEngineImpl();
        this.gameEngine.mainLoop();
    }

    private void setController(){
        this.brickController = new BrickController();
        this.collisionManager = new CollisionManager();
        this.felixController = new FelixController();
        this.gameController = new GameController();
        this.ralphController = new RalphController();
    }

    /**
     * Method to update.
     */
    public void update(){
        this.gameController.update();
    }

    /**
     * Redraw method.
     */
    public void drawUpdate(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BrickController getBrickController() {
        return this.brickController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FelixController getFelixController() {
        return this.felixController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputManager getInputManager() {
        return this.inputManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RalphController getRalphController() {
        return this.ralphController;
    }
    
}
