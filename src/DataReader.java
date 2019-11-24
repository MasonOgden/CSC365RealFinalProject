import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    public void processLine(final String line, List<Book> listOfBooks) {
        final String[] values = line.split("\t");

        if (values.length == 3) {
            for (int i = 0; i <values.length; i++) {
                System.out.print(i + ": " + values[i] + ", ");

            }
        }
        System.out.println("");
        String numCopiesString = values[3];
        String numCopies = numCopiesString.replaceAll("\"", "");
        Book newBook = new Book(values[1], values[2],
                                Integer.parseInt(numCopies), values[4]);
        listOfBooks.add(newBook);
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
            writer.println("INSERT INTO Book (image_link, title, author, numCopies, category) VALUES (\"" +
                    book.getTitle() + "\", \"" + book.getAuthor() + "\", " + book.getNumCopies() + ", \"" + book.getCategory() + "\");");
        }
        writer.close();
    }
}
