import CollectionInit.Initializer;
import Command.CommandManager;
import DataClasses.Coordinates;
import DataClasses.Person;
import DataClasses.Position;
import DataClasses.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Terminal {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Initializer initializer = new Initializer();
        LinkedList<Worker> WorkersData = initializer.initializeCollection("D:\\Workers.json");

        for(Worker worker : WorkersData){
            initializer.DataChecker(worker);
        }


        CommandManager manager = new CommandManager("D:\\Workers.json");
        String line;
        while (!(line = reader.readLine()).equals("exit")){
           WorkersData = manager.CommandHandler(line,WorkersData);
        }


    }


    /*
        WorkersData.add(new Worker(WorkersData.size()+1,
                "Vlad",
                new Coordinates(11.0F,12.0),
                ZonedDateTime.now(),
                1.20F,
                LocalDate.of(2007,3,1),
                LocalDateTime.of(LocalDate.of(2008,3,5), LocalTime.of(10,50)),
                Position.MANAGER,
                new Person(LocalDateTime.of(LocalDate.of(2008,3,5), LocalTime.of(10,50)),12,13.0F,"yepyep")));
         */
}
