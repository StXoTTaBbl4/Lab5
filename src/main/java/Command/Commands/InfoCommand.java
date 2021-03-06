package Command.Commands;

import Command.ICommand;
import DataClasses.Worker;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;

/**
 * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов).
 */
public class InfoCommand  implements ICommand {
    String path;

    /**
     *
     * @param path Путь к файлу коллекции.
     */
    public InfoCommand(String path) {
        this.path = path;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        File file = new File(path);
        Date d = new Date(file.lastModified());
        DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Тип: " + WorkersData.getClass().toString().replace("class","")+ "\n" +
                           "Дата последнего изменения: "+ dateParser.format(d) + "\n" +
                           "Кол-во элементов: " + WorkersData.size() + "\n");

        return WorkersData;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "Выводит в стандартный поток вывода информацию о коллекции\n" +
                " (тип, дата инициализации, количество элементов).";
    }
}
