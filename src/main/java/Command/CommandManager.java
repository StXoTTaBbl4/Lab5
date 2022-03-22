package Command;

import Command.Commands.*;
import Command.Commands.AddIfMax.AddIfMaxCommand;
import DataClasses.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    public List<ICommand> getCommands() {
        return commands;
    }

    public CommandManager(String path){
        addCommand(new AddCommand());
        addCommand(new AddIfMaxCommand(this));
        addCommand(new ClearCommand());
        addCommand(new FilterGtsCommand());
        addCommand(new HelpCommand(this));
        addCommand(new InfoCommand(path));
        addCommand(new RemoveIdCommand());
        addCommand(new RemoveLastCommand());
        addCommand(new SaveCommand());
        addCommand(new ShowCommand());
        addCommand(new SortCommand());
        addCommand(new UpdateIdCommand());
    }

    private void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
        if(nameFound){
            throw new IllegalArgumentException("Команда с таким именем: "+cmd.getName()+" уже существует");
        }
        commands.add(cmd);
    }


    public ICommand getCommand(String search){
        String searchLower = search.toLowerCase();

        for (ICommand cmd: this.commands) {
            if(cmd.getName().equals(searchLower)){
                return cmd;
            }
        }
        return null;
    }

    public LinkedList<Worker> CommandHandler(String input,LinkedList<Worker> WorkerData){
        String[] data = input.split(" ");

        ICommand cmd  = this.getCommand(data[0]);

        if(cmd != null){
            List<String> args = Arrays.asList(data).subList(1, data.length);

              WorkerData = cmd.handle(args.toString().substring(1,args.toString().length()-1), WorkerData);
        }else{
            System.out.println("Команда не найдена!");
        }
        return WorkerData;
    }



}
