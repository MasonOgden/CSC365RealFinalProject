package edu.calpoly.csc365.example1.entity;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Reservation {
    private int studentId;
    private int bookId;
    private int copyNum;
    private Date startDate;
    private Date endDate;
    private boolean fulfilled = false;
    private boolean expired = false;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = new Date();

    public Reservation(int studentId, int bookId, int copyNum, Date startDate, Date endDate, boolean fulfilled, boolean expired) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.copyNum = copyNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fulfilled = fulfilled;
        this.expired = expired;
    }

    public String toString() {
        if (fulfilled) {
            return "On " + startDate +
                    ", student with ID " + studentId + " reserved book with ID "
                    + bookId + ", copy number " + copyNum + ". The reservation would have expired on " + endDate +
                    ", but has already been fulfilled.";
        }
        if (!fulfilled && (endDate.compareTo(currentDate) < 0)) { // If it hasn't been fulfilled and has expired
            return "On " + startDate +
                    ", student with ID " + studentId + " reserved book with ID "
                    + bookId + ", copy number " + copyNum + ". The reservation expired on " + endDate +
                    ", and wasn't fulfilled.";
        }
        else { // If it hasn't been fulfilled and hasn't expired (is current)
            return "On " + startDate +
                    ", student with ID " + studentId + " reserved book with ID "
                    + bookId + ", copy number " + copyNum + ". The reservation will expire on " + endDate +
                    ", and hasn't fulfilled.";
        }
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

    public boolean getFulfilled(){
        return fulfilled;
    }

    public boolean getExpired(){
        return expired;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}

