package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.WindowsController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constants;

/**
 * Test class for {@link CakePositionComponent}.
 */
public final class CakePositionComponentTest {

    /**
     * The CakePositionComponent instance to be tested.
     */
    private CakePositionComponent cakePositionComponent;

    private GameController gameController;
    private GamePerformance gamePerformance;
    private WindowsController windowsController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        cakePositionComponent = new CakePositionComponent(gamePerformance);
        windowsController = new WindowsController(gamePerformance);
        windowsController.windowsGrid(Constants.Windows.NUM_WINDOWS);
    }

    /**
     * Tests the randomPosition method.
     */
    @Test
    void testRandomPosition() {
        Pair<Double, Double> position = cakePositionComponent.randomPosition();
        assertNotNull(position, "Position should not be null");
        boolean isValidPosition = gamePerformance.getWindows().stream()
                .anyMatch(window -> {
                    Pair<Double, Double> windowPos = window.getPosition();
                    return position.getX().equals(windowPos.getX() + Constants.Cake.OFFSET_X)
                            && position.getY().equals(windowPos.getY() + Constants.Cake.OFFSET_Y);
                });

        assertTrue(isValidPosition, "Position should be one of the window positions with the correct offsets");
    }

    /**
     * Tests the getComponent method.
     */
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.CAKEPOSITION, cakePositionComponent.getComponent());
    }
}
