package edu.calpoly.csc365.example1.entity;

import java.sql.Date;

public class Checkout {
    private int studentId;
    private int bookId;
    private int copyNum;
    private Date startDate;
    private Date dayReturned = null;
    private Date dueBack;
    private boolean ddExtended = false;

    public Checkout(int studentId, int bookId, int copyNum, Date startDate, Date dayReturned, Date dueBack, boolean ddExtended) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.copyNum = copyNum;
        this.startDate = startDate;
        this.dayReturned = dayReturned;
        this.dueBack = dueBack;
        this.ddExtended = ddExtended;
    }

    public String toString() {
        String returned = " not";
        String extended = " not";
        if (dayReturned != null) {
            returned = "";
        }
        if (ddExtended) {
            extended = "";
        }
        return "On " + startDate + ", student with ID "
                + studentId + " checked out book with ID "
                + bookId + ", copy number " + copyNum + ", which is due back on "
                + dueBack + ". This book has" + returned +
                " been returned. \n\tThe return date has"
                + extended + " been extended";
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId){
        this.bookId = bookId;
    }

    public int getCopyNum(){
        return copyNum;
    }

    public void setCopyNum(Integer copyNum){
        this.copyNum = copyNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getDayReturned(){
        return dayReturned;
    }

    public void setDayReturned(Date dayReturned){
        this.dayReturned= dayReturned;
    }

    public Date getDueBack() {
        return dueBack;
    }

    public void setDueBack(Date dueBack) {
        this.dueBack = dueBack;
    }

    public boolean getDdExtended(){
        return ddExtended;
    }

    public void setDdExtended(Boolean ddExtended){
        this.ddExtended = ddExtended;
    }

    public void extendDueDate(boolean ddExtended){
        this.ddExtended = ddExtended;
    }
}