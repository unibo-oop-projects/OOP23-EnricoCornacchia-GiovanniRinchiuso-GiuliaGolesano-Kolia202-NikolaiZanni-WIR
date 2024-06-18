package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.FixedWindowsComponent;
/**
 * Class to test the fixed window component.
 */
public class FixedWindowsComponentTest {

    @Test
    public void testSetFixed() {
        FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(false);
        fixedWindowsComponent.setFixed();
        assertEquals(true, fixedWindowsComponent.isFixed());
    }

    @Test
    public void testGetFixed() {
        FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(true);
        assertEquals(true, fixedWindowsComponent.isFixed());
    }

    @Test
    public void testGetComponent() {
        FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(true);
        assertEquals(ComponentType.FIXEDWINDOWS, fixedWindowsComponent.getComponent());
    }
}
