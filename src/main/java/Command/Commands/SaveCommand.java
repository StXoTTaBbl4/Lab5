package Command.Commands;

import CollectionInit.ZonedDateTimeSerializer;
import Command.ICommand;
import DataClasses.Worker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.LinkedList;

/**
 * Сохраняет коллекцию в файл.
 */
public class SaveCommand implements ICommand {


    public SaveCommand() {
    }

    @Override
    public LinkedList<Worker> handle(String path, LinkedList<Worker> WorkersData) {

        if(!Files.exists(Paths.get(path))){
            try {
                Files.createFile(Paths.get(path));
            } catch (IOException e) {
                System.out.println("Такой файл уже существует.");
            }
        }

        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer())
                        .create();
        try(Writer fw = new OutputStreamWriter(new FileOutputStream(path))){
                fw.write(gson.toJson(WorkersData));
                fw.flush();
            } catch (AccessDeniedException | FileNotFoundException e) {
            System.out.println("Ошибка доступа, невозможно записать.");
        }catch (IOException e) {
            e.printStackTrace();
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getHelp() {
        return "Сохраняет коллекцию в указанный файл.";
    }
}
