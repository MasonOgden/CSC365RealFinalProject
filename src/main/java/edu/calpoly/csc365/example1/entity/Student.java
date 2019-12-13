package edu.calpoly.csc365.example1.entity;

public class Student implements Comparable<Student>{
    private String studentType;
    private int id;
    //Not sure if we need these
    private String firstName;
    private String lastName;

    //The only thing that grad/undergrad affects is how long reservations are,
    //so that information could be stored somewhere in Reservations/Checkout
    //but that would produce duplicate information

    public Student() {
        this.studentType = null;
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
    }

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

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int compareTo(Student s){
        return this.id - s.id;
    }
}