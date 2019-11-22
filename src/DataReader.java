import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    public void processLine(final String line, List<Book> listOfBooks) {
        final String[] values = line.split(",");

        if (values.length == 0 || values.length > 7) {
            return;
        }
        else {
            // values[1] + ", " + values[2] + ", " + values[3] + ", " + values[4]
            if (values.length == 2) {
                System.out.println(line);
            }
            String numCopiesString = values[5];
            String numCopies = numCopiesString.replaceAll("\"", "");
            Book newBook = new Book(values[2], values[3], values[4],
                                    Integer.parseInt(numCopies), values[6]);
            listOfBooks.add(newBook);
        }
    }

    public void processFile(Scanner input, List<Book> listOfBooks) {
        while (input.hasNextLine()) {
            processLine(input.nextLine(), listOfBooks);
        }
    }

    public void populateListOfBooks(String filename,
                                          List<Book> listOfBooks
    ) throws FileNotFoundException {
        try (Scanner input = new Scanner(new File(filename))) {
            processFile(input, listOfBooks);
        }
    }

    public void writeBookStatementsOut(String filename,
                                       List<Book> listOfBooks)
        throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Book book : listOfBooks) {
            writer.println("INSERT INTO Book (image_link, title, author, numCopies, category) VALUES (\"" + book.getImageLink() + "\", \"" +
                    book.getTitle() + "\", \"" + book.getAuthor() + "\", " + book.getNumCopies() + ", \"" + book.getCategory() + "\");");
        }
        writer.close();
    }
}
