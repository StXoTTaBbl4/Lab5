package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

public class FilterGtsCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        Float salary = null;
        try {
            salary = Float.parseFloat(args);
        }
            catch (NumberFormatException e){
            System.out.println("Не верный тип данных. Примем: 1500.99");
        }

        for (Worker w : WorkersData) {
            try {
                if(w.getSalary() > salary)
                    System.out.println(w);
                }
            catch (NullPointerException e){
                System.out.println("Поле salary не задано у id: " + w.getId() + "\n");
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
    //filter greater then salary

}
