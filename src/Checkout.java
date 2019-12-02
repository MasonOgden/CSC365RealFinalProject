import java.util.Date;

public class Checkout {
    private int studentId;
    private int bookId;
    private Date startDate;
    private Date returnDate = null;
    private Date dueBack;
    private boolean ddExtended = false;

    public Checkout(int studentId, int bookId, Date startDate, Date dueBack, boolean ddExtended) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.dueBack = dueBack;
        this.ddExtended = ddExtended;
    }

    public String toString() {
        String returned = " not";
        String extended = " not";
        if (returnDate != null) {
            returned = "";
        }
        if (ddExtended) {
            extended = "";
        }
        return "On " + startDate + ", student with ID "
                + studentId + " checked out book with ID "
                + bookId + ", which is due back on "
                + dueBack + ". This book has" + returned +
                " been returned. The return date has"
                + extended + " been extended";
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