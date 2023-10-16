import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to manage file input from user.
 * Construct command objects that are added to an arraylist and returned to the RobotSimulator.
 *
 * @author Klara Näslund
 */

public class FileManager {
    private String fileName;
    private final ArrayList<Command> commands = new ArrayList<>();
    private boolean hasPlaceCommand = false;
    private Scanner scanner;

    /**
     * Get name of file from user.
     */
    public void getFilenameFromUser() {
        scanner = new Scanner(System.in);
        System.out.println("Name of command file: ");
        fileName = scanner.nextLine();
    }

    /**
     * Reads strings from file in directory commandFiles. Creates new command objects and adds them
     * to arraylist commands.
     * Does not add any other command objects to the list until a place command has been added.
     */

    public ArrayList<Command> readCommands() {
        try {
            File file = new File("commandFiles/" + fileName);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String command = scanner.nextLine();
                String[] tokens = command.split(" ");
                String commandName = tokens[0].toUpperCase().trim();

                switch (commandName) {
                    case "PLACE": {
                        commands.add(new PlaceCommand("PLACE", tokens));
                        setHasPlaceCommand();
                        break;
                    }
                    case "MOVE": {
                        if (hasPlaceCommand) {
                            commands.add(new Command("MOVE"));
                        }
                        break;
                    }
                    case "LEFT": {
                        if (hasPlaceCommand) {
                            commands.add(new Command("LEFT"));
                        }
                        break;
                    }
                    case "RIGHT": {
                        if (hasPlaceCommand) {
                            commands.add(new Command("RIGHT"));
                        }
                        break;
                    }
                    case "REPORT": {
                        if (hasPlaceCommand) {
                            commands.add(new Command("REPORT"));
                        }
                        break;
                    }
                    default: {
                        System.out.println("Misspelled command in file: " + commandName);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }
        return commands;
    }

    private void setHasPlaceCommand() {
        hasPlaceCommand = true;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setFileName(String fp) {
        fileName = fp;
    }

    public ArrayList<Command> runFileManager() {
        getFilenameFromUser();
        return readCommands();
    }

}
