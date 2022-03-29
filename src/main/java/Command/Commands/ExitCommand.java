package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class ExitCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        System.exit(0);
        return WorkersData;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getHelp() {
        return "Завершает программу (без сохранения в файл).";
    }
}
