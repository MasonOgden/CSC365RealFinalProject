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
    }

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
        return false;
    }

    public boolean update(Checkout object) {
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

    public boolean delete(Checkout object) {
        return false;
    }

    private Set<Checkout> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Checkout> checkouts = new HashSet<Checkout>();

        while (rs.next()) {
            Checkout checkout = new Checkout(
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getDate("startDate"),
                    rs.getDate("dueBack")
            );
            checkouts.add(checkout);
        }
        return checkouts;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

}
