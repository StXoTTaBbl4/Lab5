import CollectionInit.Initializer;
import Command.CommandManager;
import DataClasses.Worker;

import java.io.*;
import java.util.LinkedList;
/**
 *  Класс, содержащий метод main.
 */
public class Terminal {
    /** Через этот метод происходит взаимодействие пользователя с программой.
     *
     * @param args Путь к файлу с данными.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Initializer initializer = new Initializer();
        LinkedList<Worker> WorkersData = initializer.initializeCollection(args[0]);

        if(WorkersData == null) {
            WorkersData = new LinkedList<>();
        }else{
            for (Worker worker : WorkersData) {
                initializer.DataChecker(worker);
            }
        }

        CommandManager manager = new CommandManager(args[0]);
        while (true){
           WorkersData = manager.CommandHandler(reader.readLine(),WorkersData);
        }
    }
}
