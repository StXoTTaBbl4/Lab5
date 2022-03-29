package Command.Commands.AddIfMax;

import DataClasses.Worker;

import java.util.Comparator;

public class AddIfMaxComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker a, Worker b) {
        int aScore;
        int bScore;
        try {
            aScore = (int) (a.getSalary() + a.getPerson().getWeight() + a.getPerson().getHeight());
        }
        catch (NullPointerException e){
            aScore = 0;
        }
        try {
            bScore = (int) (b.getSalary() + b.getPerson().getWeight() + b.getPerson().getHeight());
        }
        catch (NullPointerException e){
            bScore = 0;
        }
        return Integer.compare(aScore - bScore, 0);
    }
}
