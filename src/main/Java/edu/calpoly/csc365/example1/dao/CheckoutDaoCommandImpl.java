package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Checkout;

import java.sql.*;

public class CheckoutDaoCommandImpl implements DaoCommand {
    private Checkout checkout;

    public CheckoutDaoCommandImpl(Checkout checkout) {
        this.checkout = checkout;
    }

    @Override
    public Object execute(DaoManager daoManager) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer rowsAffected = 0;
        try {
            conn = daoManager.getConnection();
            preparedStatement = conn.prepareStatement(
                    "INSERT INTO Checkout (studentId, bookId, copyNum, startDate, dayReturned, dueBack, ddExtended) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, checkout.getStudentId());
            preparedStatement.setInt(2, checkout.getBookId());
            preparedStatement.setInt(3, checkout.getCopyNum());
            preparedStatement.setDate(4, checkout.getStartDate());
            preparedStatement.setDate(5, checkout.getDayReturned());
            preparedStatement.setDate(6, checkout.getDueBack());
            preparedStatement.setBoolean(7, checkout.getDdExtended());
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                    checkout.setStudentId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred during SQL execution!");
        }
        return checkout;
    }
}
