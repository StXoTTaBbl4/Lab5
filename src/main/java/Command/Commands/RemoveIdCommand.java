package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class RemoveIdCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        int id;

        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("ID должно быть целым положительным числом больше 0.");
            return WorkersData;
        }

        for(Worker worker : WorkersData){
            if(worker.getId() == id){
                WorkersData.remove(worker);
                return WorkersData;
            }
        }

        System.out.println("Работника с таким id нет.");

        return WorkersData;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getHelp() {
        return "Удаляет элемент с определенным id.";
    }
}
