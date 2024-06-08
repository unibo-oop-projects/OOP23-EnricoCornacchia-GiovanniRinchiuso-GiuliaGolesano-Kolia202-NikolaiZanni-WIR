package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constants;


public class MovementComponentTest {

    private MovementComponent component;
    private Entity entity;
    private EntityFactoryImpl entityFactoryImpl;

    @BeforeEach
    public void setUp() {
        MovementComponent component = new MovementComponent(new Pair<>(0.0, 0.0));
        entityFactoryImpl = new EntityFactoryImpl(new GamePerformanceImpl(null));
        entity = entityFactoryImpl.createRalph(new Pair<>(0.0, 0.0));
    }

    @Test
    public void testMoveWithinBounds() {
        double x = 5.0;
        double y = 5.0;
        component.move(x, y, entity);
        assertEquals(new Pair<>(5.0, 5.0), entity.getPosition());
    }

    @Test
    public void testMoveOutOfRightWall() {
        entity.setPosition(new Pair<>(Constants.GameEdges.RIGHT_WALL - 1, 0.0));
        double x = 2.0;
        double y = 0.0;
        component.move(x, y, entity);
        assertEquals(new Pair<>(Constants.GameEdges.RIGHT_WALL - 1, 0.0), entity.getPosition());
    }

    @Test
    public void testCanMoveFalseWhenStopped() {
        boolean canMove = component.canMove(-10.0, -10.0, entity);
        assertFalse(canMove);
    }

    @Test
    public void testCanMoveTrueWithinBounds() {
        boolean canMove = component.canMove(5.0, 5.0, entity);
        assertTrue(canMove);
    }

    @Test
    public void testCanMoveFalseOutOfLeftWall() {
        entity.setPosition(new Pair<>(Constants.GameEdges.LEFT_WALL, 0.0));
        boolean canMove = component.canMove(-1.0, 0.0, entity);
        assertFalse(canMove);
    }
}