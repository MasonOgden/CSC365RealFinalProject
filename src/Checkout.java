import java.util.Date;

public class Checkout {
    private int studentId;
    private int bookId;
    private Date startDate;
    private Date returnDate = null;
    private Date dueBack;
    private boolean ddExtended = false;

    public Checkout(int studentId, int bookId, Date startDate, Date dueBack) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.dueBack = dueBack;
    }

    public String toString() {
        String returned = " not";
        if (returnDate != null) {
            returned = "";
        }
        return "On " + startDate + ", student with ID " + studentId + " checked out book with ID " + bookId + ", which is due back on " + dueBack + ". This book has" + returned + " been returned.";
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

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDueBack() {
        return dueBack;
    }

    public boolean isDdExtended() {
        return ddExtended;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void extendDueDate(boolean ddExtended) {
        this.ddExtended = ddExtended;
    }
}