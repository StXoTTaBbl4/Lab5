package Command.Commands.AddIfMax;

import Command.CommandManager;
import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class AddIfMaxCommand implements ICommand {

    CommandManager manager;

    public AddIfMaxCommand(CommandManager commandManager) {
        this.manager = commandManager;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        AddIfMaxComparator addIfMaxComparator = new AddIfMaxComparator();

        WorkersData.sort(addIfMaxComparator);
        LinkedList<Worker> newWorker = new LinkedList<>();
        Worker worker = manager.CommandHandler("add",newWorker).get(0);

        try{
            worker.setId(WorkersData.getLast().getId()+1);
        }
        catch (IndexOutOfBoundsException e){
            worker.setId(1);
        }

        try {
            if(addIfMaxComparator.compare(worker,WorkersData.getLast()) > 0)
                WorkersData.add(worker);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Список пуст, не с чем сравнивать.");
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getHelp() {
        return "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
