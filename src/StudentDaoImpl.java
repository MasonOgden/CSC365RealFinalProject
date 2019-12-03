import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StudentDaoImpl implements Dao<Student> {
    private Connection conn;

    public StudentDaoImpl(Connection conn) {
        this.conn = conn;
    }

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

    public int getStudentIdByName(String firstName, String lastName) {
        Integer studentId = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Student WHERE firstName=? AND lastName = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();
            Set<Student> students = unpackResultSet(resultSet);
            studentId = ((Student) students.toArray()[0]).getId();
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
        return studentId;
    }

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

    public boolean update(Student object) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Student SET studentType=?, id=?, firstName=?, lastName=? WHERE id=?");
            preparedStatement.setString(1, object.getStudentType());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.setString(3, object.getFirstName());
            preparedStatement.setString(4, object.getLastName());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Student object) {
        return false;
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

    protected void finalize() throws Throwable {
        super.finalize();
    }

}