package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.api.Entity;
import java.util.HashSet;
import java.util.Set;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixedWindowsComponent;

public class WindowsController {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private Set<Entity> entities;
    private boolean state;
    @SuppressWarnings("unused")
    private int cont;
    private int windowWidth = 80; // Larghezza di ogni finestra
    private int windowHeight = 80; // Altezza di ogni finestra
    private int windowSpacing = 20; // Spaziatura tra le finestre
    private int gridRows = 3; // Numero di righe della griglia
    private int gridCols = 3; // Numero di colonne della griglia
    private int gridOffsetX = 50; // Offset X della griglia
    private int gridOffsetY = 50; // Offset Y della griglia

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
        //AGGIUNGERE BOOLEANO PER DECIDERE SE LA FINESTRA E' ROTTA O MENO DECREMENTANTO BROKEN
        this.cont = broken;
        this.entities = new HashSet<>();
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridCols; col++) {
                double x = gridOffsetX + col * (windowWidth + windowSpacing);
                double y = gridOffsetY + row * (windowHeight + windowSpacing);
                entities.add(this.entityFactoryImpl.createWindows(new Pair<>(x,y), state));

            }   
        }
        return this.entities;
    }
}
