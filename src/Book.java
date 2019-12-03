public class Book {
    private int id;
    private String title;
    private String author;
    private int numCopies;
    private String category;

    public Book(int id, String title, String author, int num_copies, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numCopies = num_copies;
        this.category = category;
    }

    public String toString() {
        return "title: " + title + ", author: " + author + ", number of copies: " + numCopies + ", category: " + category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public String getCategory() {
        return category;
    }
}