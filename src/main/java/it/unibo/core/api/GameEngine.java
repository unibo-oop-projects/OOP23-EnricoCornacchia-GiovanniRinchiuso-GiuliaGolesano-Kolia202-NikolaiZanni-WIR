package it.unibo.core.api;

public interface GameEngine {

    void mainLoop();

    void update();

    void waitForNextFrame();
    
    void cleanup();
}

