package Command;

import DataClasses.Worker;

import java.util.LinkedList;

public interface ICommand {
    LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData);

    String getName();

    String getHelp();

}
