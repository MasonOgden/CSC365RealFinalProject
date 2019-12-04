import java.util.Date;

public class Reservation {
    private int studentId;
    private int bookId;
    private int copyNum;
    private Date startDate;
    private Date endDate;
    private boolean fulfilled = false;

    public Reservation(int studentId, int bookId, int copyNum, Date startDate, Date endDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.copyNum = copyNum;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        String myFulfilled = " not";
        if (fulfilled) {
            myFulfilled = "";
        }
        return "On " + startDate +
                ", student with ID " + studentId + " reserved book with ID "
                + bookId + ", copy number " + copyNum + ". The reservation expires on " + endDate +
                ", and has" + myFulfilled + " been fulfilled.";
    }

    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }
    public int getCopyNum() {
        return copyNum;
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