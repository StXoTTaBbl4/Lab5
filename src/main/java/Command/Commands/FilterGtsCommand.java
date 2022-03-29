package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

/**
 * Выводит элементы, значение поля salary которых больше заданного.
 */
public class FilterGtsCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        Float salary = null;
        try {
            salary = Float.parseFloat(args);
        }
            catch (NumberFormatException e){
            System.out.println("Не верный тип данных. Пример: 1500.99.");
        }

        for (Worker w : WorkersData) {
            try {
                if(w.getSalary() > salary)
                    System.out.println(w);
                }
            catch (NullPointerException e){
                if(w.getSalary() == null)
                System.out.printf("Поле salary не задано у id: %s.\n", w.getId());
            }
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "filter_greater_than_salary";
    }

    @Override
    public String getHelp() {
        return "Выводит элементы, значение поля salary которых больше заданного.";
    }
}
