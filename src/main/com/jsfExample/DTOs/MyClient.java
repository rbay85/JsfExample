package main.com.jsfExample.DTOs;


public class MyClient {

    private String firstName;
    private String lastName;
    private String birthDay;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBirthDay() {
        return birthDay;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + " " + birthDay + "\n";
    }
}
