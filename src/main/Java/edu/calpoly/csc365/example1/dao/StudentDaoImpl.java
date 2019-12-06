package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Student;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class StudentDaoImpl implements Dao<Student> {
    private Connection conn;

    public StudentDaoImpl(Connection conn) { this.conn = conn; }

    @Override
    public Student getById(int id) {
        Student student = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Student WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Set<Student> students = unpackResultSet(resultSet);
            student = (Student) students.toArray()[0];
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public Set<Student> getAll() {
        Set<Student> students = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Student");
            resultSet = preparedStatement.executeQuery();
            students = unpackResultSet(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Integer insert (Student obj) {
        Integer id = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement(
                    "INSERT INTO Student (studentType, firstName, lastName) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getStudentType());
            preparedStatement.setString(2, obj.getFirstName());
            preparedStatement.setString(3, obj.getLastName());
            int numRows = preparedStatement.executeUpdate();
            if (numRows == 1) {
                // get generated id
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                    id = resultSet.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public Integer update(Student object) {
        Integer numRows = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conn.prepareStatement(
                    "UPDATE Student SET studentType=?, firstName=?, lastName=? WHERE id=?");
            preparedStatement.setString(1, object.getStudentType());
            preparedStatement.setString(2, object.getFirstName());
            preparedStatement.setString(3, object.getLastName());
            preparedStatement.setInt(4, object.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return numRows;
        } finally{
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return numRows;
    }

    @Override
    public Integer delete(Student obj) {
        Integer numRows = 0;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = this.conn.prepareStatement("DELETE FROM Student WHERE id = ?");
            preparedStatement.setInt(1, obj.getId());
            numRows = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return numRows;
    }

    private Set<Student> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Student> students = new HashSet<Student>();

        while (rs.next()) {
            Student student = new Student(
                    rs.getString("studentType"),
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
            );
            students.add(student);
        }
        return students;
    }

}
