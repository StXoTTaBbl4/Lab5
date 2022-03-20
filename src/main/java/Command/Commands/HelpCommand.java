package Command.Commands;

import Command.CommandManager;
import Command.ICommand;
import DataClasses.Worker;

import java.util.LinkedList;


public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        if(args.isEmpty()){
            StringBuilder builder = new StringBuilder();

            builder.append("List of commands\n");

            manager.getCommands().stream().map(ICommand::getName).forEach((it) -> builder.append(it).append('\n'));
            System.out.println(builder);
        }else {

            ICommand command = manager.getCommand(args);

            if (command == null) {
                System.out.printf("Команда + %s не найдена", args);
            }else{
                System.out.println(command.getHelp());
            }
        }
        return WorkersData;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "С помощью этой команды можно получить справку по другим командам \n" +
                "help [имя команды]";
    }
}
