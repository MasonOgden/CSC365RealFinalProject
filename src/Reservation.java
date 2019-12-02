import java.util.Date;

public class Reservation {
    private int studentId;
    private int bookId;
    private Date startDate;
    private Date endDate;
    private boolean fulfilled = false;

    public Reservation(int studentId, int bookId, Date startDate, Date endDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}