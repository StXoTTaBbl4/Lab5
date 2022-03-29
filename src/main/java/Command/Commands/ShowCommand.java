package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class ShowCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

            if(WorkersData.size() == 0) {
                System.out.println("Коллекция пуста.");
                return WorkersData;
            }
            else {
                for (Worker worker : WorkersData) {
                    try {
                        System.out.println(worker.toString());
                    } catch (NullPointerException e) {
                        System.out.println("У работника с id: " + worker.getId() + " некорректные данные.\n");
                    }
                }
            }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getHelp() {
        return "Выводит список элементов коллекции.";
    }
}
