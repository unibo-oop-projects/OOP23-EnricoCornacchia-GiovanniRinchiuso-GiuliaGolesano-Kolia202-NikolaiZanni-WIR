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

public class WindowsController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Set<Entity> entities;
    private int WINDOW_WIDTH = 53;
    private int WINDOW_HEIGHT = 80;
    private int WINDOW_SPACING = 0;
    private int GRID_ROWS = 3;
    private int GRID_COLS = 5;
    private int GRID_OFFSET_X = 278;
    private int GRID_OFFSET_Y = 310;
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
     * Method that create the map according to the level.
     * @param level
     * @return
     */
    public Set<Entity> windowsGrid(final int broken) {
        this.entities = new HashSet<>();
        List<Boolean> windowStates = new ArrayList<>(Collections.nCopies(GRID_ROWS * GRID_COLS, false));
        for (int i = 0; i < broken; i++) {
            windowStates.set(i, true);
        }
        Collections.shuffle(windowStates);

        int index = 0;
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                if (index >= windowStates.size()) break;
                double x = GRID_OFFSET_X + col * (WINDOW_WIDTH + WINDOW_SPACING);
                double y = GRID_OFFSET_Y + row * (WINDOW_HEIGHT + WINDOW_SPACING);
                boolean state = windowStates.get(index);
                Entity window = this.entityFactoryImpl.createWindows(new Pair<>(x, y), state);
                entities.add(window);
                index++;
            }
        }
        return this.entities;
    }
}