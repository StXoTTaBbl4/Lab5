package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class RemoveIdCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        int id = Integer.parseInt(args);

        Worker toRemove = null;

        for(Worker worker : WorkersData){
            if(worker.getId() == id){
                toRemove = worker;
                break;
            }
        }

        if(toRemove==null){
            System.out.println("Работника с таким id нет");
        }else
            WorkersData.remove(toRemove);

        return WorkersData;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getHelp() {
        return "Удаляет элемент с определенным id";
    }
}
