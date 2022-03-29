package Command.Commands;

import Command.CommandManager;
import Command.ICommand;
import DataClasses.Worker;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ExecuteScriptCommand implements ICommand {

    private final CommandManager manager;

    public ExecuteScriptCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        Scanner scanner = null;
        LinkedList<Worker> localList = WorkersData;

        if(args.equals("")){
            System.out.println("Вы должны указать путь к файлу.");
            System.exit(0);
        }
        else {
            try {
                scanner = new Scanner(new FileInputStream(args));
            } catch (FileNotFoundException e) {
                System.out.println("Указанного файла не существует.");
                return WorkersData;
            }
        }

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println("Выполнение команды: " + line + ".\n");
            localList = manager.CommandHandler(line, localList);
        }

        System.out.println("Скрипт завершен:\nпринять результат - 1,\nотменить - 2,\nпоказать результат - 3.");

        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                switch (reader.readLine()) {
                    case "1":
                        return localList;
                    case "2":
                        return WorkersData;
                    case "3":
                        for (Worker w:localList) {
                            System.out.println(w.toString());
                        }

                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getHelp() {
        return "Считывает и исполняет команды из указанного файла, синтаксис команд аналогичен пользовательским.";
    }
}
