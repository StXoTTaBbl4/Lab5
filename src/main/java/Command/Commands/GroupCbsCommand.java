package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class GroupCbsCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        return WorkersData;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getHelp() {
        return null;
    }
    //group counting by salary

}
