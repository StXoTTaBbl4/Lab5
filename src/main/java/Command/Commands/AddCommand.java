package Command.Commands;

import Command.ICommand;
import DataClasses.Coordinates;
import DataClasses.Person;
import DataClasses.Position;
import DataClasses.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.util.Collections;
import java.util.LinkedList;

public class AddCommand implements ICommand {

    @Override
    public LinkedList<Worker> handle(String args,LinkedList<Worker> WorkerData) {

        Collections.sort(WorkerData);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Worker worker;
        Integer id;
        String name;
        Coordinates coordinates;
        Float salary;
        LocalDate startDate;
        LocalDateTime endDate;
        Position position;
        Person person;
        String passportID;
        //LocalDateTime personBD;
        //int height;
        //Float weight;

        try{
            id = WorkerData.getLast().getId()+1;
        }
        catch (IndexOutOfBoundsException e){
            id = 1;
        }

        try {
            while (true) {
                System.out.println("Поле name:(не может быть пустым):");
                name = reader.readLine().trim();
                if(!name.equals(""))
                break;
                else {
                    System.out.println("Поле name не может быть пустым");
                }
            }

            while (true) {
                try {
                    System.out.println("Поле X для coordinates(не может быть пустым):");
                    Float x = Float.parseFloat(reader.readLine());
                    System.out.println("Поле Y для coordinates(не может быть пустым):");
                    Double y = Double.parseDouble(reader.readLine());
                    coordinates = new Coordinates(x, y);
                    break;
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка типа данных");
                }
            }

            while (true) {
                try {
                    System.out.println("Поле salary(не может быть пустым или меньше 0):");
                    salary = Float.parseFloat(reader.readLine());
                    break;
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка типа данных");
                }
            }

            while (true) {
                try {
                    System.out.println("Поле startDate(формат: Г-М-Д, не может быть пустым):");
                    String[] stData = reader.readLine().split("-");
                    startDate = LocalDate.of(Integer.parseInt(stData[0]),
                            Integer.parseInt(stData[1]),
                            Integer.parseInt(stData[2]));
                    break;
                }
                catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Ошибка в данных, пример: 2000-10-15");
                }
            }

            while (true) {
                try {
                    System.out.println("Поле endDate(формат: Г-М-Д Ч:М):");
                    String s = reader.readLine();
                    if(s.equals("")){
                        endDate = null;
                    }
                    else {
                        String[] enData = s.split(" ");
                        String[] ed = enData[0].split("-");
                        String[] et = enData[1].split(":");
                        endDate = LocalDateTime.of(Integer.parseInt(ed[0]),
                                Integer.parseInt(ed[1]),
                                Integer.parseInt(ed[2]),
                                Integer.parseInt(et[0]),
                                Integer.parseInt(et[1]));
                    }
                    break;
                }
                catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Ошибка в данных, пример: 2000-10-15");
                }
            }

            while (true) {
                try {
                    System.out.println("Поле position, доступные: \nMANAGER\nBAKER\nCOOK\nMANAGER_OF_CLEANING\n");
                    position = Position.valueOf(reader.readLine());
                    break;
                }catch (ArrayIndexOutOfBoundsException e){
                    position = null;
                }
                catch (IllegalArgumentException e){
                    System.out.println("Некорректные данные. Пример: MANAGER");
                }
            }

            person = new Person();
            while (true) {
                try {
                    System.out.println("Полe birthday(формат: Г-М-Д Ч:М):");
                    String[] PData = reader.readLine().split(" ");
                    String[] ed = PData[0].split("-");
                    String[] et = PData[1].split(":");
                    /*
                    personBD = LocalDateTime.of(Integer.parseInt(ed[0]),
                            Integer.parseInt(ed[1]),
                            Integer.parseInt(ed[2]),
                            Integer.parseInt(et[0]),
                            Integer.parseInt(et[1]));
                    */
                    person.setBirthday(LocalDateTime.of(Integer.parseInt(ed[0]),
                            Integer.parseInt(ed[1]),
                            Integer.parseInt(ed[2]),
                            Integer.parseInt(et[0]),
                            Integer.parseInt(et[1])));
                    break;
                } catch (DateTimeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Некорректные данные поля birthday. Пример: 2000-10-12 16:35");
                }
            }

                while (true) {
                    try {
                        System.out.println("Полe height(больше 0, не может быть пустым):");
                        //height = Integer.parseInt(reader.readLine());
                        person.setHeight(Integer.parseInt(reader.readLine()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный тип данных.");
                    }
                }

                while (true) {
                    try {
                        System.out.println("Полe weight(больше 0):");
                        //weight = Float.parseFloat(reader.readLine());
                        person.setWeight(Float.parseFloat(reader.readLine()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный тип данных.");
                    }
                }

                while (true) {
                    try {
                        System.out.println("Полe passportID(от 4 до 29 символов, не может быть пустым):");
                        passportID = reader.readLine();

                        if(passportID.length()<29 && passportID.length() > 4)
                            person.setPassportID(passportID);
                        else
                            throw new IOException();
                        break;
                    } catch (IOException e) {
                        System.out.println("Некорректная длина.");
                    }
                }



            worker = new Worker(id,
                    name,
                    coordinates,
                    ZonedDateTime.now(),
                    salary,
                    startDate,
                    endDate,
                    position,
                    person);

            WorkerData.add(worker);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return WorkerData;
        }

        /*
        Gson gson = new Gson();
        Worker worker = gson.fromJson("["+args+"]",Worker.class);
        WorkerData.add(worker);
         */

        @Override
        public String getName () {
            return "add";
        }

        @Override
        public String getHelp () {
            return "Добавляет в коллекцию новый элемент с указанными параметрами, ввод данных построчно";
        }

    }

