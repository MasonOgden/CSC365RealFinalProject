import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

// Maybe consider adding a "checkedOut" boolean to book to make EVERYTHING easier

public class Main {
    public static void main(String[] args) {
        String credFile = args[0];
        Properties prop = new Properties();
        FileInputStream fis = null;
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
            Dao<Reservation> reservationDao = daoManager.getReservationDao();
            Set<Reservation> reservations = reservationDao.getAll();
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }

            // Printing out all checkouts currently in the database
            Dao<Checkout> checkoutDao = daoManager.getCheckoutDao();
            Set<Checkout> checkouts = checkoutDao.getAll();
            for (Checkout checkout : checkouts) {
                System.out.println(checkout);
            }

            ((CheckoutDaoImpl)checkoutDao).extendReturnDate(1, 1, 1, 5);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}