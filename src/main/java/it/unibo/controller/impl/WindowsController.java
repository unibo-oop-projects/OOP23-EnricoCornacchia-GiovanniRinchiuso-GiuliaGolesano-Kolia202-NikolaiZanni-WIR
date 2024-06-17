package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.api.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixedWindowsComponent;

/**
 * Class to control a window entity.
 */
public class WindowsController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Set<Entity> entities;
    private final int WIDTH = 53;
    private final int HEIGHT = 80;
    private final int SPACING = -1;
    private final int ROWS = 3;
    private final int COLS = 5;
    private final int OFFSET_X = 280;
    private final int OFFSET_Y = 317;
    /**
     * Constructor.
     * @param gamePerformance the game performance.
     */
    public WindowsController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
    }
    /**
     * Method that check if there are broken windows.
     * @return true if the game is won, false otherwise.
     */
    public boolean won() {
        return this.gamePerformance.getWindows().stream()
            .allMatch(w -> w.getTheComponent(ComponentType.FIXEDWINDOWS)
                            .map(c -> ((FixedWindowsComponent) c).getFixed())
                            .orElse(false));
    }
    /**
     * Method that create the grid according to the level.
     * @param broken
     * @return the set of entities.
     */
    public Set<Entity> windowsGrid(final int broken) {
        this.entities = new HashSet<>();
        List<Boolean> windowStates = new ArrayList<>(Collections.nCopies(ROWS * COLS, true));
        for (int i = 0; i < broken; i++) {
            windowStates.set(i, false);
        }
        Collections.shuffle(windowStates);

        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (index >= windowStates.size()) break;
                double x = OFFSET_X + col * (WIDTH + SPACING);
                double y = OFFSET_Y + row * (HEIGHT + SPACING);
                boolean state = windowStates.get(index);
                Entity window = this.entityFactoryImpl.createWindows(new Pair<>(x, y), state);
                entities.add(window);
                index++;
                this.gamePerformance.addEntity(window);
            }
        }
        
        return this.entities;
    }
}
