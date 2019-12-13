package edu.calpoly.csc365.example1.entity;

public class BookUsage implements Comparable<BookUsage>{
     private String bookId;
     private int january;
     private int february;
     private int march;
     private int april;
     private int may;
     private int june;
     private int july;
     private int august;
     private int september;
     private int october;
     private int november;
     private int december;
     private int bookTotal;

    public BookUsage(String bookId, int january, int february, int march, int april,
                     int may, int june, int july, int august, int september, int october,
                     int november, int december, int bookTotal) {
        this.setBookId(bookId);
        this.january = january;
        this.february = february;
        this.march = march;
        this.setApril(april);
        this.setMay(may);
        this.setJune(june);
        this.setJuly(july);
        this.setAugust(august);
        this.setSeptember(september);
        this.setOctober(october);
        this.setNovember(november);
        this.setDecember(december);
        this.setBookTotal(bookTotal);
    }

    public int compareTo(BookUsage o){
        return(Integer.parseInt(this.bookId) - Integer.parseInt(o.bookId));
    }
/*
    public BookUsage(String bookId, int january, int february, int march,
                     int april, int may, int june, int july, int august,
                     int september, int october, int november,
                     int december, int bookTotal) {
        this.bookId = bookId;
        tanuary = january;
        February = february;
        March = march;
        April = april;
        May = may;
        June = june;
        July = july;
        August = august;
        September = september;
        October = october;
        November = november;
        December = december;
        this.bookTotal = bookTotal;
    }
    */

    public int getJanuary() {
        return january;
    }

    public int getFebruary() {
        return february;
    }

    public int getMarch() {
        return march;
    }

    public int getApril() {
        return april;
    }

    public int getMay() {
        return may;
    }

    public int getJune() {
        return june;
    }

    public int getJuly() {
        return july;
    }

    public int getAugust() {
        return august;
    }

    public int getSeptember() {
        return september;
    }

    public int getOctober() {
        return october;
    }

    public int getNovember() {
        return november;
    }

    public int getDecember() {
        return december;
    }

    public String getBookId() {
        return bookId;
    }

    public int getBookTotal() {
        return bookTotal;
    }

    /*
    public String toString() {
        if (bookId.equals("Total")) {
            return bookId + "\t|" + January + "\t\t\t|" + February + "\t\t\t|" + March + "\t\t |" + April + "\t\t\t|" + May + "\t\t|" + June + "\t\t|" + July + "\t\t|" + August + "\t\t\t|" + September + "\t\t\t|" + October + "\t\t\t|" + November + "\t\t\t|" + December + "\t\t\t|" + bookTotal + "\t\t |";
        }
        else {
            return bookId + "\t\t|" + January + "\t\t\t|" + February + "\t\t\t|" + March + "\t\t |" + April + "\t\t\t|" + May + "\t\t|" + June + "\t\t|" + July + "\t\t|" + August + "\t\t\t|" + September + "\t\t\t|" + October + "\t\t\t|" + November + "\t\t\t|" + December + "\t\t\t|" + bookTotal + "\t\t |";
        }
    }*/

    public int getBookIdAsInt() {
        return Integer.parseInt(getBookId());
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setApril(int april) {
        this.april = april;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public void setJune(int june) {
        this.june = june;
    }

    public void setJuly(int july) {
        this.july = july;
    }

    public void setAugust(int august) {
        this.august = august;
    }

    public void setSeptember(int september) {
        this.september = september;
    }

    public void setOctober(int october) {
        this.october = october;
    }

    public void setNovember(int november) {
        this.november = november;
    }

    public void setDecember(int december) {
        this.december = december;
    }

    public void setBookTotal(int bookTotal) {
        this.bookTotal = bookTotal;
    }
}

