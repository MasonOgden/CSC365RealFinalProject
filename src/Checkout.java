import java.util.Date;

public class Checkout {
    private int studentId;
    private int bookId;
    private Date startDate;
    private Date dueBack;

    public Checkout(int studentId, int bookId, Date startDate, Date dueBack) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.dueBack = dueBack;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDueBack() {
        return dueBack;
    }
}