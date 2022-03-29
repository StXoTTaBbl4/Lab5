package CollectionInit;

import DataClasses.Worker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Initializer {

    public void DataChecker(Worker worker){
        if(worker.getName().equals(null) || worker.getName().equals(""))
            System.out.printf("У работника с id: %s не задано имя \n", worker.getId());
        if(worker.getCoordinates() == null)
            System.out.printf("У работника с id: %s не заданы координаты \n", worker.getId());
        if(worker.getCoordinates().getX() == null)
            System.out.printf("У работника с id: %s не задана координата X\n", worker.getId());
        if(worker.getCoordinates().getY() == null)
            System.out.printf("У работника с id: %s не задана координата Y\n", worker.getId());
        if(worker.getCreationDate()== null)
            System.out.printf("У работника с id: %s не задано время создания файла \n", worker.getId());
        if(worker.getSalary() == null || worker.getSalary() <= 0)
            System.out.printf("У работника с id: %s не установлена зарплата \n", worker.getId());
        if(worker.getStartDate() == null)
            System.out.printf("У работника с id: %s не задано поле startDate \n", worker.getId());
        if(worker.getPerson() == null)
            System.out.printf("У работника с id: %s не задано поле person \n", worker.getId());
        if(worker.getPerson().getHeight() <= 0)
            System.out.printf("У работника с id: %s некорректно задано поле height(должно быть >0) \n", worker.getId());
        if(worker.getPerson().getWeight() <= 0)
            System.out.printf("У работника с id: %s некорректно задано поле weight(должно быть >0) \n", worker.getId());
        if(worker.getPerson().getPassportID().length() > 29 || worker.getPerson().getPassportID().length() < 4)
            System.out.printf("У работника с id: %s некорректно задано поле passportID(3<x<30) \n", worker.getId());
    }

    public LinkedList<Worker> initializeCollection(String path){
        LinkedList<Worker> WorkersData = null;

        if(path.equals("")){
            System.out.println("Вы должны указать путь к файлу");
            System.exit(0);
        }
        else{
            Scanner scanner = null;
            try{
                scanner = new Scanner(new FileInputStream(path));
            }
            catch (FileNotFoundException e){
                System.out.println("Указанного файла не существует");
                System.exit(0);
            }

            StringBuilder GsonData = new StringBuilder();
            while(scanner.hasNext())
                GsonData.append(scanner.next());

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ZonedDateTime.class,new ZonedDateTimeDeserializer())
                    .create();

            Type type = new TypeToken<LinkedList<Worker>>() {}.getType();
            try {
                WorkersData = gson.fromJson(String.valueOf(GsonData), type);
            }
            catch (IllegalStateException ignored){
            }

        }
        return WorkersData;
    }
}
