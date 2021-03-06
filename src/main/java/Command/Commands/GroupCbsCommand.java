package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;

/**
 * Группирует элементы коллекции по значению поля salary, выводит количество элементов в каждой группе.
 */
public class GroupCbsCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        if(WorkersData.size() == 0) {
            System.out.println("Коллекция пуста.");
        }
        else if(WorkersData.size() < 2) {
            System.out.printf("В коллекции всего 1 элемент с зарплатой %s.\n", WorkersData.get(0).getSalary());
        }
        else {
            float minSalary = Integer.MAX_VALUE,
                                maxSalary = 0,
                                middleSalary,
                                midMinSalary,
                                midMaxSalary;

            for (Worker w : WorkersData) {
                if (w.getSalary() < minSalary)
                    minSalary = w.getSalary();
                if (w.getSalary() > maxSalary)
                    maxSalary = w.getSalary();
            }

            if(maxSalary == minSalary){
                System.out.printf("Все элементы коллекции имеют зарплату %s.\n", minSalary);
            }
            else {
                middleSalary = (minSalary + maxSalary) / 2;
                midMinSalary = (middleSalary + minSalary) / 2;
                midMaxSalary = (middleSalary + maxSalary) / 2;

                printData(minSalary, midMinSalary, WorkersData);
                printData(midMinSalary + 0.1F, midMaxSalary, WorkersData);
                printData(midMaxSalary + 0.1F, maxSalary, WorkersData);
            }
        }
        return WorkersData;
    }

    @Override
    public String getName() {
        return "group_counting_by_salary";
    }

    @Override
    public String getHelp() {
        return "Группирует элементы коллекции по значению поля salary, выводит количество элементов в каждой группе.";
    }

    private void printData(float leftBorder, float rightBorder, LinkedList<Worker> WorkersData){
        System.out.println("Зарплата от " + leftBorder + " до " + rightBorder);
        int k = 0;
        for (Worker w: WorkersData) {
            if(w.getSalary() >= leftBorder && w.getSalary() <= rightBorder)
                k++;
        }
        System.out.println(k + "\n");
    }


}
