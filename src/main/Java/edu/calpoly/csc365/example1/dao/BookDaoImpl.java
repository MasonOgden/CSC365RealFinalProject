package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Book;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class BookDaoImpl implements Dao<Book>{
    private Connection conn;

    public BookDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
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

    @Override
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

    @Override
    public Integer insert (Book obj) {
        Integer id = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.conn.prepareStatement(
                    "INSERT INTO Book (title, author, numCopies, category) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setInt(3, obj.getCopyNum());
            preparedStatement.setString(4, obj.getCategory());
            int numRows = preparedStatement.executeUpdate();
            if (numRows == 1) {
                // get generated id
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next())
                    id = resultSet.getInt(1);
            }
        }catch(SQLException e){
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
    public Integer update(Book obj) {
        Integer numRows = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conn.prepareStatement(
                    "UPDATE Book SET title=?, author=?, num_copies=?, category=? WHERE id=?");
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setInt(3, obj.getCopyNum());
            preparedStatement.setString(4, obj.getCategory());
            preparedStatement.setInt(5, obj.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return numRows;
        }finally {
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
    public Integer delete(Book obj) {
        Integer numRows = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conn.prepareStatement("DELETE FROM Book WHERE id = ?");
            preparedStatement.setInt(1, obj.getId());
            numRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numRows;
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

}