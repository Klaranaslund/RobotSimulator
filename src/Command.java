/**
 * Class for creating commands.
 * Arguably this should be an interface that would be implemented by a separate class for each command.
 *
 * @author Klara Näslund
 */
public class Command {
    private final String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
