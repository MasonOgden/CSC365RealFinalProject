package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Student;
import edu.calpoly.csc365.example1.entity.StudentInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StudentInfoDaoImpl implements Dao<StudentInfo> {
    private Connection conn;

    public StudentInfoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public StudentInfo getById(int id) {return null;}

    public Set<StudentInfo> getAll() {
        Set<StudentInfo> students = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM StudentInfo");
            resultSet = preparedStatement.executeQuery();
            students = unpackResultSet(resultSet);
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
        return students;
    }

    public boolean insert (Student object) {
        return false;
    }

    public Integer update(StudentInfo object) {
        return 1;
    }

    public Integer insert(StudentInfo object) {
        return 1;
    }

    public Integer delete(StudentInfo object) {
        return 1;
    }

    private Set<StudentInfo> unpackResultSet(ResultSet rs) throws SQLException {
        Set<StudentInfo> studentsInfo = new HashSet<StudentInfo>();

        while (rs.next()) {
            StudentInfo studentInfo = new StudentInfo(
                    rs.getString("sType"),
                    rs.getString("sName"),
                    rs.getInt("numDays"),
                    rs.getInt("maxNumBooks")
            );
            studentsInfo.add(studentInfo);
        }
        return studentsInfo;
    }

    public Integer getNumDays(String sType) {
        Integer numDays = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM StudentInfo WHERE sType=?");
            preparedStatement.setString(1, sType);
            resultSet = preparedStatement.executeQuery();
            Set<StudentInfo> siSet = unpackResultSet(resultSet);
            numDays = ((StudentInfo)siSet.toArray()[0]).getNumDays();
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
        return numDays;
    }
}