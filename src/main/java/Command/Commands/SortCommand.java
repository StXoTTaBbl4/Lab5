package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Сортирует коллекцию в естественном порядке(по ID).
 */
public class SortCommand implements ICommand {

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        try {
            Collections.sort(WorkersData);
        }catch (NullPointerException e){
            System.out.println("Ошибка при сортировке, провертье поля Coordinates, Person, Salary. Они не должны быть null.");
        }


        return WorkersData;
    }

    @Override
    public String getName() {
        return "sort";
    }

    @Override
    public String getHelp() {
        return "Сортирует коллекцию в естественном порядке(по ID).";
    }
}
