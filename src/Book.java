public class Book {
    private int id;
    private String image_link;
    private String title;
    private String author;
    private int numCopies;
    private String category;

    public Book(int id, String image_link, String title, String author, int num_copies, String category) {
        this.id = id;
        this.image_link = image_link;
        this.title = title;
        this.author = author;
        this.numCopies = num_copies;
        this.category = category;
    }

    public String toString() {
        return "id: " + id + ", title: " + title + ", author: " + author + ", number of copies: " + numCopies + ", category: " + category;
    }

    public String getImageLink() {
        return image_link;
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