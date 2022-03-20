package Command.Commands.AddIfMax;

import DataClasses.Worker;

import java.util.Comparator;

public class AddIfMaxComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker a, Worker b) {

        int aScore = (int) (a.getSalary() + a.getPerson().getWeight() + a.getPerson().getHeight());
        int bScore = (int) (b.getSalary() + b.getPerson().getWeight() + b.getPerson().getHeight()) ;

        return Integer.compare(aScore - bScore, 0);
    }
}
