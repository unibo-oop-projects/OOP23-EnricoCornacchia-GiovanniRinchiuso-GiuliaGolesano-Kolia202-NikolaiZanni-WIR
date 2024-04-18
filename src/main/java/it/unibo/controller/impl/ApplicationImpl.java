package it.unibo.controller.impl;

import it.unibo.controller.api.Application;

public class ApplicationImpl implements Application {

    private BrickController brickController;
    private CollisionManager collisionManager;
    private FelixController felixController;
    private GameController gameController;
    private InputManager inputManager;
    private RalphController ralphController;

    /**
     * Constructor.
     */
    public ApplicationImpl() {
        this.brickController = new BrickController();
        this.collisionManager = new CollisionManager();
        this.felixController = new FelixController();
        this.gameController = new GameController();
        this.inputManager = new InputManager();
        this.ralphController = new RalphController();
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
