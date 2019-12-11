import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Maybe consider adding a "checkedOut" boolean to book to make EVERYTHING easier

public class Main {
    public static void main(String[] args) {
        String credFile = args[0];
        Properties prop = new Properties();
        FileInputStream fis = null;
        Date today = new Date();
        try {
            fis = new FileInputStream(credFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.loadFromXML(fis);
            DaoManager daoManager = new DaoManager(new ConnectionFactory(
                    prop.getProperty("driver"),
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("pass")
            ));

            // Printing out all books currently in the database
            /*Dao<Book> bookDao = daoManager.getBookDao();
            Set<Book> books = bookDao.getAll();
            for (Book book : books) {
                System.out.println(book);
            }*/

            // Printing out all students currently in the database
            /*Dao<Student> studentDao = daoManager.getStudentDao();
            Set<Student> students = studentDao.getAll();
            for (Student student : students) {
                System.out.println(student);
            }*/

            // Printing out all reservations currently in the database
            /*Dao<Reservation> reservationDao = daoManager.getReservationDao();
            Set<Reservation> reservations = reservationDao.getAll();
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }*/

            // Printing out all checkouts currently in the database
            Dao<Checkout> checkoutDao = daoManager.getCheckoutDao();
            Set<BookUsage> bookUsages = ((CheckoutDaoImpl)checkoutDao).getUsageSummary("2019");
            System.out.println("bookId\t| January\t| February\t| March\t | April\t| May\t| June\t| July\t| August\t| September\t| October\t| November\t| December\t| total\t |");
            Comparator<BookUsage> compByBookId = Comparator.comparing(BookUsage::getBookIdAsInt);
            BookUsage[] usages = new BookUsage[bookUsages.size()];
            usages = bookUsages.toArray(usages);
            Arrays.sort(usages, compByBookId);
            for (BookUsage bs : usages) {
                System.out.println(bs);
            }

            System.out.println(((CheckoutDaoImpl)checkoutDao).getUsageSummaryColumnTotals("2019"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}