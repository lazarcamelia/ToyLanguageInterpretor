package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu() {
        this.commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    public void printMenu() {
        System.out.println("The options are the following:");

        for (Command command : commands.values()) {
            String commandLine = String.format("%4s : %s", command.getKey(), command.getDescription());
            System.out.println(commandLine);
        }
    }

    public void show() throws RuntimeException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.println("Your option is: ");
            String key = scanner.nextLine();

            Command command = commands.getOrDefault(key, null);
            if (command != null) {
                command.execute();
            }
            else
                System.out.println("Option not found!");

        }
    }
}
