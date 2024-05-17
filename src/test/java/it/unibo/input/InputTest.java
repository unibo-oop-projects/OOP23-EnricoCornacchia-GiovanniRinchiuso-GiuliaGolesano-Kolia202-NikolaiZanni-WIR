package it.unibo.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import it.unibo.controller.impl.GameController;
import it.unibo.view.impl.WindowGame;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class InputTest {

    private WindowGame windowGame;
    private GameController gameController;
    private GamePerformance gamePerformance;

    @Start
    private void start(Stage stage) throws Exception {
        windowGame = new WindowGame();
        windowGame.start(stage);
    }

    @BeforeEach
    public void setup() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
    }

    @Test
    public void testKeyPresses(FxRobot robot) {
        robot.press(KeyCode.W).release(KeyCode.W);
        assertTrue(gamePerformance.getInputs().contains(KeyCode.W), "KeyCode W should be in the input list");

        robot.press(KeyCode.A).release(KeyCode.A);
        assertTrue(gamePerformance.getInputs().contains(KeyCode.A), "KeyCode A should be in the input list");

        robot.press(KeyCode.S).release(KeyCode.S);
        assertTrue(gamePerformance.getInputs().contains(KeyCode.S), "KeyCode S should be in the input list");

        robot.press(KeyCode.D).release(KeyCode.D);
        assertTrue(gamePerformance.getInputs().contains(KeyCode.D), "KeyCode D should be in the input list");

        robot.press(KeyCode.Z);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(gamePerformance.getInputs().contains(KeyCode.Z), "KeyCode Z should be in the input list");
    }
}
