public class Student {
    private boolean gradStudent;
    private int id;

    //Not sure if we need these
    private int firstName;
    private int lastName;

    //The only thing that grad/undergrad affects is how long reservations are,
    //so that information could be stored somewhere in Reservations/Checkout
    //but that would produce duplicate information


    public Student(boolean gradStudent, int id, int firstName, int lastName) {
        this.gradStudent = gradStudent;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        String studentType = "undergraduate";
        if (gradStudent) {
            studentType = "graduate";
        }
        return studentType + " student, id: " + id + ", first name: " + firstName + ", last name: " + lastName;
    }

    public boolean isGradStudent() {
        return gradStudent;
    }

    public int getId() {
        return id;
    }

    public int getFirstName() {
        return firstName;
    }

    public int getLastName() {
        return lastName;
    }
}
