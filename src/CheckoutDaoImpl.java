import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CheckoutDaoImpl implements Dao<Checkout> {
    private Connection conn;

    public CheckoutDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public Checkout getById(int id) {
        return null;
    } // Must be here to implement the interface, but we don't have an Id for Checkout

    // Returns all current checkouts of the student
    public Set<Checkout> getActiveByStudentId(int id) {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout WHERE studentId = ? AND dayReturned is null");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return checkouts;
    }

    // Returns all past checkouts by the student
    public Set<Checkout> getInactiveByStudentId(int id) {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout WHERE studentId = ? AND dayReturned is not null");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return checkouts;
    }

    public Set<Checkout> getAll() {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout");
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return checkouts;
    }

    public boolean insert (Checkout object) {
        // This is what's used to 'check out' a book
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "insert into Checkout (studentId, bookId, startDate, dayReturned, dueBack) values (?, ?, ?, null, ?)");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setDate(3, (Date) object.getStartDate());
            preparedStatement.setDate(3, (Date) object.getDueBack());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean extendReturnDate(int studentId, int bookId, int copyNum, int numDays) {
        // This method needs to take into account whether someone else has reserved it already.
        // Can't extend a checkout on a book if someone else has a reservation for it. Need to implement that.
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET dueBack = DATE_ADD(dueBack, INTERVAL ? DAY), ddExtended = 1 WHERE studentId=? AND bookId = ? AND copyNum = ?;");
            preparedStatement.setInt(1, numDays);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setInt(4, copyNum);
            preparedStatement.execute();
        }
        catch (SQLException e) { // If someone has reserved this book, my trigger will raise an error.
            //System.out.println(e.getMessage());
            if (e.getMessage().equals("Cannot extend because someone has a reservation on this book")) {
                System.out.println(e.getMessage());
            }
            else {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean update(Checkout object) {
        // Need to fix this one
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET studentId=?, bookId=?, startDate=?, dayReturned=?, dueBack = ?, ddExtended = ? WHERE id=?");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setDate(3, (Date) object.getStartDate());
            preparedStatement.setDate(4, (Date) object.getReturnDate());
            preparedStatement.setDate(4, (Date) object.getDueBack());
            preparedStatement.setBoolean(4, object.isDdExtended());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean returnBook(int studentId, int bookId, int copyNum, Date dayReturned) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET dayReturned=? WHERE bookId = ? AND copyNum = ? AND studentId = ?");
            preparedStatement.setDate(1, dayReturned);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, copyNum);
            preparedStatement.setInt(3, studentId);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Checkout object) {
        return false;
    }

    private Set<Checkout> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Checkout> checkouts = new HashSet<Checkout>();

        while (rs.next()) {
            Checkout checkout = new Checkout(
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getInt("copyNum"),
                    rs.getDate("startDate"),
                    rs.getDate("dueBack"),
                    rs.getBoolean("ddExtended")
            );
            checkouts.add(checkout);
        }
        return checkouts;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

}
