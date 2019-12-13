package edu.calpoly.csc365.example1.entity;

public class Book implements Comparable<Book>{
    private int id;
    private int copyNum;
    private String title;
    private String author;
    private String category;

    public Book(int id, int copyNum, String title, String author, String category) {
        this.id = id;
        this.copyNum = copyNum;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String toString() {
        return "title: " + title + ", author: " + author + ", copy number: " + copyNum + ", category: " + category;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public String getCategory() {
        return category;
    }

    public int compareTo(Book b){
        return this.id - b.id;
    }
}