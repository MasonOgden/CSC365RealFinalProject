package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Reservation;
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
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Reservation WHERE studentId = ? AND fulfilled = 0");
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

    public Integer insert (Reservation obj) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id = null;
        int numRows;

        try {
            preparedStatement = this.conn.prepareStatement(
                    "INSERT INTO Reservation (studentId, bookId, copyNum, startDate, fulfilled, expired) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, obj.getStudentId());
            preparedStatement.setInt(2, obj.getBookId());
            preparedStatement.setInt(3, obj.getCopyNum());
            preparedStatement.setDate(4, (Date)obj.getStartDate());
            preparedStatement.setInt(5, obj.getFulfilled() ? 1 : 0);
            preparedStatement.setInt(6, obj.getExpired() ? 1 : 0);
            numRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        System.out.println("Inserted " + numRows);
        return 0;
    }

    public Integer update(Reservation object) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Reservation SET studentId=?, bookId=?, copyNum = ? startDate=?, endDate=? WHERE id=?");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setInt(3, object.getCopyNum());
            preparedStatement.setDate(4, (Date) object.getStartDate());
            preparedStatement.setDate(5, (Date) object.getEndDate());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Integer delete(Reservation object) {
        return 1;
    }

    private Set<Reservation> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Reservation> reservations = new HashSet<Reservation>();

        while (rs.next()) {
            Reservation reservation = new Reservation(
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getInt("copyNum"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getBoolean("fulfilled"),
                    rs.getBoolean("expired")
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}