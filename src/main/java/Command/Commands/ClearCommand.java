package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.Collections;
import java.util.LinkedList;

public class ClearCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {


        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Очищает коллекцию";
    }
}
