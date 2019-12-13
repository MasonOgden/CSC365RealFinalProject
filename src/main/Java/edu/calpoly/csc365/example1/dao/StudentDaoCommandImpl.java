package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Student;

import java.sql.*;

public class StudentDaoCommandImpl implements DaoCommand {
    private Student student;

    public StudentDaoCommandImpl(Student student) {
        this.student = student;
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
                    "INSERT INTO Student (studentType, id, firstName, lastName) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getStudentType());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                    student.setId(resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new RuntimeException("Error occurred during SQL execution!");
        }
        return student;
    }

}
