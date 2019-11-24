import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BookDaoImpl implements Dao<Book> {
    private Connection conn;

    public BookDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public Book getById(int id) {
        Book book = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Set<Book> books = unpackResultSet(resultSet);
            book = (Book)books.toArray()[0];
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    public Set<Book> getAll() {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book");
            resultSet = preparedStatement.executeQuery();
            books = unpackResultSet(resultSet);
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
        return books;
    }

    public boolean insert (Book object) {
        return false;
    }

    public boolean update(Book object) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Book SET title=?, author=?, num_copies=?, category=? WHERE id=?");
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.setInt(3, object.getNumCopies());
            preparedStatement.setString(4, object.getCategory());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Book object) {
        return false;
    }

    private Set<Book> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Book> books = new HashSet<Book>();

        while (rs.next()) {
            Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("numCopies"),
                    rs.getString("category")
            );
            books.add(book);
        }
        return books;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}