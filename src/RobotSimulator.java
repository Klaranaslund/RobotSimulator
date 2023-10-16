import java.util.ArrayList;

/**
 * Implements a Robot Simulator.
 *
 * @author Klara Näslund
 */

public class RobotSimulator {
    private final TableTop tableTop;
    private final FileManager fileManager;
    private ArrayList<Command> commands;

    public RobotSimulator() {
        tableTop = new TableTop();
        fileManager = new FileManager();
    }

    /**
     * Sets arraylist commands via filemanager.
     * Print error message if no valid commands are present in the file.
     */
    private void setUpCommands() {
        commands = fileManager.runFileManager();
        if (commands == null) {
            return;
        }
        if (commands.isEmpty()) {
            System.out.println("No valid commands in command file.");
        }
    }

    /**
     * Run command loop.
     */
    private void commandLoop() {
        try {
            for (Command command : commands) {

                switch (command.getCommandName()) {
                    case "PLACE": {
                        if (command instanceof PlaceCommand) {
                            int xPos = ((PlaceCommand) command).getX();
                            int yPos = ((PlaceCommand) command).getY();
                            Direction d = ((PlaceCommand) command).getDirection();
                            tableTop.placeRobot(xPos, yPos, d);
                            break;
                        }
                    }
                    case "MOVE": {
                        tableTop.move();
                        break;
                    }
                    case "LEFT": {
                        tableTop.getRobot().leftTurn();
                        break;
                    }
                    case "RIGHT": {
                        tableTop.getRobot().rightTurn();
                        break;
                    }
                    case "REPORT": {
                        tableTop.report();
                        break;
                    }
                }
            }
        } catch (NullPointerException ignored) {

        }
    }


    public ArrayList<Command> getCommands() {
        return commands;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     * Run the robot simulator.
     */
    public void run() {
        setUpCommands();
        commandLoop();
    }
}
