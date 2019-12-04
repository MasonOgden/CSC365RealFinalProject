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
        return null;
    }

    public Set<Book> getByBookId(int id) {
        // This is gonna return 3 books, cause id is no longer unique.
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            books = unpackResultSet(resultSet);
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
        return books;
    }

    public Book getByIdAndCopy(int id, int copyNum) {
        // This is gonna return 1 book
        Book book = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE id=? AND copyNum = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, copyNum);
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

    public int getIdByTitle(String bookTitle) {
        Integer bookId = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE title=?");
            preparedStatement.setString(1, bookTitle);
            resultSet = preparedStatement.executeQuery();
            Set<Book> books = unpackResultSet(resultSet);
            bookId = ((Book)books.toArray()[0]).getId();
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
        return bookId;
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
                    "UPDATE Book SET title=?, author=?, category=? WHERE bookId=? AND copyNum = ?");
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.setString(3, object.getCategory());
            preparedStatement.setInt(4, object.getId());
            preparedStatement.setInt(5, object.getCopyNum());
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
                    rs.getInt("id"),
                    rs.getInt("copyNum"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category")
            );
            books.add(book);
        }
        return books;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    public Set<Book> searchByTitle(String title) {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE title = ?");
            preparedStatement.setString(1, title);
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

    public Set<Book> searchByAuthor(String author) {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE author = ?");
            preparedStatement.setString(1, author);
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

    public Set<Book> searchByCategory(String category) {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Book WHERE category = ?");
            preparedStatement.setString(1, category);
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
}