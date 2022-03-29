import CollectionInit.Initializer;
import Command.CommandManager;
import DataClasses.Worker;

import java.io.*;
import java.util.LinkedList;

public class Terminal {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Initializer initializer = new Initializer();
        LinkedList<Worker> WorkersData = initializer.initializeCollection("D:\\tmp.json");

        if(WorkersData == null) {
            WorkersData = new LinkedList<>();
        }else{
            for (Worker worker : WorkersData) {
                initializer.DataChecker(worker);
            }
        }

        CommandManager manager = new CommandManager("D:\\tmp.json");
        while (true){
           WorkersData = manager.CommandHandler(reader.readLine(),WorkersData);
        }
    }
}
