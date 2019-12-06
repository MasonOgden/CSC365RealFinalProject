package edu.calpoly.csc365.example1.entity;

public class Student {
    private String studentType;
    private int id;
    //Not sure if we need these
    private String firstName;
    private String lastName;

    //The only thing that grad/undergrad affects is how long reservations are,
    //so that information could be stored somewhere in Reservations/Checkout
    //but that would produce duplicate information


    public Student(String studentType, int id, String firstName, String lastName) {
        this.studentType = studentType; // this is one character: "u" or "g"
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return studentType + " student, id: " + id + ", first name: " + firstName + ", last name: " + lastName;
    }

    public String getStudentType() {
        return studentType;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
