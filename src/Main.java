import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Book> listOfBooks = new ArrayList<Book>();
        DataReader dataReader = new DataReader();
        String credFile = args[0];
        String dataFile = args[1];
        String statementFile = args[2];
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(credFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Read in data from .csv file
        try {
            // Problem right now is that a title has commas in it, which is messing up my split function.
            dataReader.populateListOfBooks(dataFile, listOfBooks);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        // Write the SQL INSERT statements to a file
        try {
            dataReader.writeBookStatementsOut(statementFile, listOfBooks);
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Test that connection is working by printing out every book in the database
        /*try {
            prop.loadFromXML(fis);
            DaoManager daoManager = new DaoManager(new ConnectionFactory(
                    prop.getProperty("driver"),
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("pass")
            ));
            Dao<Book> bookDao = daoManager.getBookDao();
            Set<Book> books = bookDao.getAll();
            for (Book book : books) {
                System.out.println(book);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}