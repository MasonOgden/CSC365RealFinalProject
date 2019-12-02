import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ReservationDaoImpl implements Dao<Reservation> {
    private Connection conn;

    public ReservationDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public Reservation getById(int id) {
        return null;
    }

    public Set<Reservation> getUnfulfilledByStudentId(int id) {
        Set<Reservation> reservations = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Reservation WHERE studentId = ? AND fulfilled = false");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            reservations = unpackResultSet(resultSet);
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
        return reservations;
    }

    public Set<Reservation> getAll() {
        Set<Reservation> reservations = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Reservation");
            resultSet = preparedStatement.executeQuery();
            reservations = unpackResultSet(resultSet);
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
        return reservations;
    }

    public boolean insert (Reservation object) {
        return false;
    }

    public boolean update(Reservation object) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Reservation SET studentId=?, bookId=?, startDate=?, endDate=? WHERE id=?");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setDate(3, (Date) object.getStartDate());
            preparedStatement.setDate(4, (Date) object.getEndDate());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Reservation object) {
        return false;
    }

    private Set<Reservation> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Reservation> reservations = new HashSet<Reservation>();

        while (rs.next()) {
            Reservation reservation = new Reservation(
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate")
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}
