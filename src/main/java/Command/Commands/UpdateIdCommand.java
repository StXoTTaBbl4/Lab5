package Command.Commands;

import Command.ICommand;
import DataClasses.Coordinates;
import DataClasses.Person;
import DataClasses.Position;
import DataClasses.Worker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class UpdateIdCommand implements ICommand {


    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkerData) {

        String[] data = args.replaceAll(",", "").split(" ");

        Worker worker = null;
        for (Worker w: WorkerData) {
            if(w.getId() == Integer.parseInt(data[0])) {
                worker = w;
            }
        }
        try {
            WorkerData.remove(worker);
        }
        catch (NoSuchElementException e){
            System.out.println("нету такого");
        }


        if(worker == null)
            System.out.println("Работник с таким ID не найден");
        else {
            switch (data[1]){
                case "name":
                    try {
                        worker.setName(data[2]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле name не может быть пустым");
                    }
                    break;
                case"coordinates":
                    try {
                        Coordinates coordinates = new Coordinates(Float.parseFloat(data[2]),Double.parseDouble(data[3]));
                        worker.setCoordinates(coordinates);
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле x или y не может быть пустым");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Некорректный тип данных для x или y");
                    }
                    break;
                case"salary":
                    try {
                        if(Float.parseFloat(data[2]) > 0)
                            worker.setSalary(Float.parseFloat(data[2]));
                        else
                            System.out.println("salary не может быть 0");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Некорректный тип данных для salary");
                    }
                    catch (ArrayIndexOutOfBoundsException t){
                        worker.setSalary(null);
                    }
                    break;
                case"startDate":
                    try {
                        String[] sd = data[2].split("-");
                        worker.setStartDate(LocalDate.of(Integer.parseInt(sd[0]),
                                Integer.parseInt(sd[1]),
                                Integer.parseInt(sd[2])));
                    }
                    catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Некорректные данные. Формат: Г-М-Д");
                    }
                    break;
                case"endDate":
                    String[] ed = data[2].split("-");
                    String[] et = data[3].split(":");

                    try {
                        worker.setEndDate(LocalDateTime.of(Integer.parseInt(ed[0]),
                                Integer.parseInt(ed[1]),
                                Integer.parseInt(ed[2]),
                                Integer.parseInt(et[0]),
                                Integer.parseInt(et[1])));
                    }
                    catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e){
                        System.out.println("Некорректные данные. Формат: Г-М-Д Ч:М");
                    }
                    break;
                case"position":
                    try {
                        worker.setPosition(Position.valueOf(data[2]));
                    }catch (ArrayIndexOutOfBoundsException e){
                        worker.setPosition(null);
                    }
                    catch (IllegalArgumentException e){
                        System.out.println("Некорректные данные. Пример: MANAGER");
                    }
                    break;
                case"person":
                    Person person = new Person();

                    //birthday
                    try {
                        String[] edPerson = data[2].split("-");
                        String[] etPerson = data[3].split(":");
                        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(edPerson[0]),
                                Integer.parseInt(edPerson[1]),
                                Integer.parseInt(edPerson[2]),
                                Integer.parseInt(etPerson[0]),
                                Integer.parseInt(etPerson[1]));
                        person.setBirthday(localDateTime);
                    }
                    catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e ) {
                        System.out.println("Некорректные данные поля birthday. Формат: Г-М-Д Ч:М");
                    }

                    //height
                    try {
                        if(Integer.parseInt(data[4]) > 0)
                        person.setHeight(Integer.parseInt(data[4]));
                        else
                            System.out.println("Параметр height должен быть больше 0");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Некорректный тип данных для salary");
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле salary не может быть пустым");
                    }

                    //weight
                    try {
                        if(data[5].equals("null"))
                            person.setWeight(null);
                        else if(Float.parseFloat(data[5]) > 0)
                            person.setWeight(Float.parseFloat(data[5]));
                        else
                            System.out.println("weight не может быть 0");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Некорректный тип данных для salary");
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле salary не может быть пустым");
                    }

                    //passportID
                    try{
                        if(data[6].length() < 1)
                            throw new IllegalArgumentException();
                        else if(data[6].length() > 29 || data[6].length() < 4)
                            throw new IllegalArgumentException();
                        else
                            person.setPassportID(data[6]);
                    }catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле passportID не должно быть пустым или\n выходить за границы 4 - 29 символов");
                    }
                    worker.setPerson(person);
                    break;
                default:
                    System.out.println("Поле не найдено");
            }
            WorkerData.add(worker);
        }


        return WorkerData;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getHelp() {
        return "Обновляет параметры элемента коллекции с указанным ID";
    }
}
/*
                case"y":
                    try {
                        worker.getCoordinates().setY(Double.parseDouble(data[2]));
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Поле y не может быть пустым");
                    }
                    catch (ClassCastException e){
                        System.out.println("Некорректный тип данных для y");
                    }
                    break;
                     */