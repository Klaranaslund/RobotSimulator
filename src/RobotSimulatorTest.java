import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Klara Näslund
 * @version JUnit 5
 */

public class RobotSimulatorTest {

    /**
     * Testing that the robot is not falling of the edges of the tabletop when it is placed
     * in one of the four corners, facing away from the board and then calling the move command.
     */
    @Test
    public void testMovingOutOfBounds() {
        TableTop tableTop = new TableTop();
        tableTop.placeRobot(5, 5, Direction.NORTH);
        Robot robot = tableTop.getRobot();

        tableTop.move();
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
        Assertions.assertEquals(5, tableTop.getXCoordinate());
        Assertions.assertEquals(5, tableTop.getYCoordinate());

        tableTop.placeRobot(1, 5, Direction.WEST);
        tableTop.move();
        Assertions.assertEquals(Direction.WEST, robot.getDirection());
        Assertions.assertEquals(1, tableTop.getXCoordinate());
        Assertions.assertEquals(5, tableTop.getYCoordinate());

        tableTop.placeRobot(1, 1, Direction.SOUTH);
        tableTop.move();
        Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
        Assertions.assertEquals(1, tableTop.getXCoordinate());
        Assertions.assertEquals(1, tableTop.getYCoordinate());

        tableTop.placeRobot(5, 1, Direction.EAST);
        tableTop.move();
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
        Assertions.assertEquals(5, tableTop.getXCoordinate());
        Assertions.assertEquals(1, tableTop.getYCoordinate());
    }

    /**
     * Testing that a robot is not placed if the x- och y-coordinates are invalid.
     */
    @Test
    public void testInvalidPlaceRobot() {
        TableTop tableTop = new TableTop();
        tableTop.placeRobot(7, 10, Direction.NORTH);
        Assertions.assertFalse(tableTop.getRobot().getPlaced());
        tableTop.placeRobot(-4, -4, Direction.EAST);
        Assertions.assertFalse(tableTop.getRobot().getPlaced());
    }

    /**
     * Testing that move-command moves robot to the right position
     * on the tabletop.
     */
    @Test
    public void testMoveRobot() {
        TableTop tableTop = new TableTop();
        tableTop.placeRobot(2, 2, Direction.EAST);
        tableTop.move();
        Robot robot = tableTop.getRobot();
        Assertions.assertEquals(3, tableTop.getXCoordinate());
        Assertions.assertEquals(2, tableTop.getYCoordinate());
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
        tableTop.move();
        Assertions.assertEquals(4, tableTop.getXCoordinate());
        Assertions.assertEquals(2, tableTop.getYCoordinate());
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
    }

    /**
     * Testing a full turn to the left.
     */
    @Test
    public void testLeftTurnRobot() {
        Robot robot = new Robot();
        robot.setPlaced();
        robot.setDirection(Direction.NORTH);
        robot.leftTurn();
        Assertions.assertEquals(Direction.WEST, robot.getDirection());
        robot.leftTurn();
        Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
        robot.leftTurn();
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
        robot.leftTurn();
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
    }

    /**
     * Testing a full turn to the right.
     */

    @Test
    public void testRightTurnRobot() {
        Robot robot = new Robot();
        robot.setPlaced();
        robot.setDirection(Direction.NORTH);
        robot.rightTurn();
        Assertions.assertEquals(Direction.EAST, robot.getDirection());
        robot.rightTurn();
        Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
        robot.rightTurn();
        Assertions.assertEquals(Direction.WEST, robot.getDirection());
        robot.rightTurn();
        Assertions.assertEquals(Direction.NORTH, robot.getDirection());
    }

    /**
     * Testing expected output from report command.
     */
    @Test
    public void testReportRobot() {
        TableTop tableTop = new TableTop();
        tableTop.placeRobot(1, 2, Direction.SOUTH);
        String expectedOutput = "Robot is at: 1, 2 facing SOUTH";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        tableTop.report();
        String actualOutput = outputStream.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Testing that no commands are stored in command-list if file input does not contain a valid place-command.
     * This ensures that all commands are ignored by tabletop and robot until a valid place command has been read.
     */
    @Test
    public void testReadCommands() {
        RobotSimulator robotSimulator = new RobotSimulator();
        robotSimulator.getFileManager().setFileName("commandFiles/InvalidCommandFile");
        Assertions.assertNull(robotSimulator.getCommands());

        robotSimulator.getFileManager().setFileName("commandFiles/CommandFile");
        Assertions.assertNotNull(robotSimulator.getFileManager().getCommands());
    }
}
