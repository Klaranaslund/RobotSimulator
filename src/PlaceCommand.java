/**
 * Class for creating a place command, extending the Command class.
 *
 * @author Klara Näslund
 */

public class PlaceCommand extends Command {
    private String[] tokens;
    private int x;
    private int y;
    private Direction direction;

    /**
     * Constructor for a place command.
     *
     * @param commandName name of the command, sent to superclass Command.
     * @param tokens      array of strings containing the separate parts of a place command.
     */
    public PlaceCommand(String commandName, String[] tokens) {
        super(commandName);
        this.tokens = tokens;
        placeParser();
    }

    /**
     * Parses a command string that specifies coordinates and direction, and updates the state of the place command.
     * The method expects a command string in the format of "PLACE X,Y,F", where X and Y are integer values representing the position on the tabletop,
     * and F is a string representing the direction the object is facing.
     */
    public void placeParser() {
        if (tokens.length == 2) {
            String[] args = tokens[1].split(",");

            if (args.length == 3) {
                this.x = Integer.parseInt(args[0].trim());
                this.y = Integer.parseInt(args[1].trim());
                direction = Direction.valueOf(args[2].trim().toUpperCase());
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
