package pro.prysm.orion.common.command;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:30 PM
 * This file was created as a part of Orion
 */
public class CommandHandlerThread extends Thread {
    private final Scanner scanner;
    private final CommandHandler commandHandler;

    public CommandHandlerThread(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String[] rawArr = scanner.nextLine().split(" ");
            String name = rawArr[0];
            String[] args = Arrays.copyOfRange(rawArr, 1, rawArr.length);
            commandHandler.handleCommand(name, args);
        }
    }
}
