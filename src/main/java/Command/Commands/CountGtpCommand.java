package Command.Commands;

import Command.ICommand;
import DataClasses.Person;
import DataClasses.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.LinkedList;

//Count greater then person
public class CountGtpCommand implements ICommand {
    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = new Person();
        AddCommand addCommand = new AddCommand();
        person = addCommand.createNewPerson(reader, person);

        for (Worker w: WorkersData) {
            try {
                if (compare(w.getPerson(), person) > 0)
                    System.out.println(w);
            }
            catch (NullPointerException e){
                System.out.println("Поле person не задано у id: " + w.getId() + "\n");
            }
        }

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

    private int compare(Person a, Person b){
        int aScore = 0;
        int bScore = 0;

        try {
            aScore = (int) (a.getHeight() + a.getWeight());
            bScore = (int) (b.getHeight() + b.getWeight()) ;
        }catch (NullPointerException e){
            System.out.println("Ошибка в данных.");
        }
        return aScore - bScore;
    }

}
