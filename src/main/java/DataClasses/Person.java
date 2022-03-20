package DataClasses;

import java.time.LocalDateTime;

public class Person {
    private java.time.LocalDateTime birthday;
    private int height;// >0
    private Float weight;// Null or >0
    private String passportID;// NotNull, not empty, 3<l<30

    public Person(LocalDateTime birthday, int height, Float weight, String passportID) {
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    public Person(){}

    public int getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Birthday: " + birthday + "\n" +
                "Height: " + height + "\n" +
                "Weight: " + weight + "\n" +
                "Passport ID: " + passportID + "\n";
    }
}
