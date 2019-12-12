package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckoutDaoImpl implements Dao<Checkout> {
    private Connection conn;

    public CheckoutDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public Checkout getById(int id) {
        return null;
    } // Must be here to implement the interface, but we don't have an Id for Checkout

    // Returns all current checkouts of the student
    public Set<Checkout> getActiveByStudentId(int id) {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout WHERE studentId = ? AND dayReturned is null");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
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
        return checkouts;
    }

    // Returns all past checkouts by the student
    public Set<Checkout> getInactiveByStudentId(int id) {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout WHERE studentId = ? AND dayReturned is not null");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
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
        return checkouts;
    }

    public Set<Checkout> getAll() {
        Set<Checkout> checkouts = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("SELECT * FROM Checkout");
            resultSet = preparedStatement.executeQuery();
            checkouts = unpackResultSet(resultSet);
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
        return checkouts;
    }

    public Integer insert (Checkout object) {
        // This is what's used to 'check out' a book
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "insert into Checkout (studentId, bookId, startDate, dayReturned, dueBack) values (?, ?, ?, null, ?)");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setDate(3, (Date) object.getStartDate());
            preparedStatement.setDate(3, (Date) object.getDueBack());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return object.getStudentId();
    }

    public boolean extendReturnDate(int studentId, int bookId, int copyNum, int numDays) {
        // This method needs to take into account whether someone else has reserved it already.
        // Can't extend a checkout on a book if someone else has a reservation for it. Need to implement that.
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET dueBack = DATE_ADD(dueBack, INTERVAL ? DAY), ddExtended = 1 WHERE studentId=? AND bookId = ? AND copyNum = ?;");
            preparedStatement.setInt(1, numDays);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setInt(4, copyNum);
            preparedStatement.execute();
        }
        catch (SQLException e) { // If someone has reserved this book, my trigger will raise an error.
            //System.out.println(e.getMessage());
            if (e.getMessage().equals("Cannot extend because someone has a reservation on this book")) {
                System.out.println(e.getMessage());
            }
            else {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Set<Book> getCurrentlyCheckedOutBooks() {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("select bookId as id, Checkout.copyNum, title, author, category from Checkout join Book on dayReturned is null and Checkout.bookId = Book.id and Checkout.copyNum = Book.copyNum;");
            resultSet = preparedStatement.executeQuery();
            books = unpackResultSetBook(resultSet);
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

    public Set<Book> getPastDueBooks(java.util.Date todaysDate) {
        Set<Book> books = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String todayString = new SimpleDateFormat("yyyy-MM-dd").format(todaysDate);

        try {
            preparedStatement = this.conn.prepareStatement("select id, Checkout.copyNum, title, author, category from Checkout join Book on dueBack < ? and dayReturned is null and id = bookId and Checkout.copyNum = Book.copyNum;");
            preparedStatement.setString(1, todayString);
            resultSet = preparedStatement.executeQuery();
            books = unpackResultSetBook(resultSet);
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

    public Integer update(Checkout object) {
        // Need to fix this one
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET studentId=?, bookId=?, startDate=?, dayReturned=?, dueBack = ?, ddExtended = ? WHERE id=?");
            preparedStatement.setInt(1, object.getStudentId());
            preparedStatement.setInt(2, object.getBookId());
            preparedStatement.setDate(3, (Date) object.getStartDate());
            preparedStatement.setDate(4, (Date) object.getReturnDate());
            preparedStatement.setDate(4, (Date) object.getDueBack());
            preparedStatement.setBoolean(4, object.getDdExtended());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return object.getBookId();
    }

    public boolean returnBook(int studentId, int bookId, int copyNum, Date dayReturned) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE Checkout SET dayReturned=? WHERE bookId = ? AND copyNum = ? AND studentId = ?");
            preparedStatement.setDate(1, dayReturned);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, copyNum);
            preparedStatement.setInt(3, studentId);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Set<BookUsage> getUsageSummary(String year) {
        Set<BookUsage> bookUsages = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("select *, (January + February + March + April + May + June + July + August + September + October + November + December) as bookTotal from (select bookId, max( if(monthNum = 1, numCheckouts, 0)) as January, max( if(monthNum = 2, numCheckouts, 0)) as February, max( if(monthNum = 3, numCheckouts, 0)) as March, max( if(monthNum = 4, numCheckouts, 0)) as April, max( if(monthNum = 5, numCheckouts, 0)) as May, max( if(monthNum = 6, numCheckouts, 0)) as June, max( if(monthNum = 7, numCheckouts, 0)) as July, max( if(monthNum = 8, numCheckouts, 0)) as August, max( if(monthNum = 9, numCheckouts, 0)) as September, max( if(monthNum = 10, numCheckouts, 0)) as October, max( if(monthNum = 11, numCheckouts, 0)) as November, max( if(monthNum = 12, numCheckouts, 0)) as December from (select bookId, month(startDate) as monthNum, count(*) as numCheckouts from Checkout where year(startDate) = ? group by bookId, month(startDate)) as bookMonthlyCheckoutCounts group by bookId) as usages");
            preparedStatement.setString(1, year);
            resultSet = preparedStatement.executeQuery();
            //System.out.println("size of resultset: " + resultSet.getFetchSize());
            bookUsages = unpackResultSetBookUsage(resultSet);
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
        return bookUsages;
    }

    public BookUsage getUsageSummaryColumnTotals(String year) {
        BookUsage bookUsage = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.conn.prepareStatement("select 0 as bookId, sum(January) as januaryTotal, sum(February) as februaryTotal, sum(March) as marchTotal, sum(April) as aprilTotal, sum(May) as mayTotal, sum(June) as juneTotal, sum(July) as julyTotal, sum(August) as augustTotal, sum(September) as septemberTotal, sum(October) as octoberTotal, sum(November) as novemberTotal, sum(December) as decemberTotal, sum(booktotal) as overallTotal from (select *, (January + February + March + April + May + June + July + August + September + October + November + December) as bookTotal from (select bookId, max( if(monthNum = 1, numCheckouts, 0)) as January, max( if(monthNum = 2, numCheckouts, 0)) as February, max( if(monthNum = 3, numCheckouts, 0)) as March, max( if(monthNum = 4, numCheckouts, 0)) as April, max( if(monthNum = 5, numCheckouts, 0)) as May, max( if(monthNum = 6, numCheckouts, 0)) as June, max( if(monthNum = 7, numCheckouts, 0)) as July, max( if(monthNum = 8, numCheckouts, 0)) as August, max( if(monthNum = 9, numCheckouts, 0)) as September, max( if(monthNum = 10, numCheckouts, 0)) as October, max( if(monthNum = 11, numCheckouts, 0)) as November, max( if(monthNum = 12, numCheckouts, 0)) as December from (select bookId, month(startDate) as monthNum, count(*) as numCheckouts from Checkout where year(startDate) = ? group by bookId, month(startDate)) as bookMonthlyCheckoutCounts group by bookId) as usages) as usages2");
            preparedStatement.setString(1, year);
            resultSet = preparedStatement.executeQuery();
            //System.out.println("size of resultset: " + resultSet.getFetchSize());
            bookUsage = unpackColumnTotal(resultSet);
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
        return bookUsage;
    }

    public Integer delete(Checkout object) {
        return -1;
    }

    private Set<Checkout> unpackResultSet(ResultSet rs) throws SQLException {
        Set<Checkout> checkouts = new HashSet<Checkout>();

        while (rs.next()) {
            Checkout checkout = new Checkout(
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getInt("copyNum"),
                    rs.getDate("startDate"),
                    rs.getDate("dueBack"),
                    rs.getBoolean("ddExtended")
            );
            checkouts.add(checkout);
        }
        return checkouts;
    }

    private Set<Book> unpackResultSetBook(ResultSet rs) throws SQLException {
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

    private Set<BookUsage> unpackResultSetBookUsage(ResultSet rs) throws SQLException {
        Set<BookUsage> bookUsages= new HashSet<>();

        while (rs.next()) {
            BookUsage bookUsage = new BookUsage(
                    String.valueOf(rs.getInt("bookId")),
                    rs.getInt("January"),
                    rs.getInt("February"),
                    rs.getInt("March"),
                    rs.getInt("April"),
                    rs.getInt("May"),
                    rs.getInt("June"),
                    rs.getInt("July"),
                    rs.getInt("August"),
                    rs.getInt("September"),
                    rs.getInt("October"),
                    rs.getInt("November"),
                    rs.getInt("December"),
                    rs.getInt("bookTotal")
            );
            bookUsages.add(bookUsage);
        }
        return bookUsages;
    }

    private BookUsage unpackColumnTotal(ResultSet rs) throws SQLException {
        List<BookUsage> bookUsages= new ArrayList<>();

        while (rs.next()) {
            BookUsage bookUsage = new BookUsage(
                    "Total",
                    rs.getInt("januaryTotal"),
                    rs.getInt("februaryTotal"),
                    rs.getInt("marchTotal"),
                    rs.getInt("aprilTotal"),
                    rs.getInt("mayTotal"),
                    rs.getInt("juneTotal"),
                    rs.getInt("julyTotal"),
                    rs.getInt("augustTotal"),
                    rs.getInt("septemberTotal"),
                    rs.getInt("octoberTotal"),
                    rs.getInt("novemberTotal"),
                    rs.getInt("decemberTotal"),
                    rs.getInt("overallTotal")
            );
            bookUsages.add(bookUsage);
        }
        return bookUsages.get(0);
    }

    /*protected void finalize() throws Throwable {
        super.finalize();
    }*/

}
