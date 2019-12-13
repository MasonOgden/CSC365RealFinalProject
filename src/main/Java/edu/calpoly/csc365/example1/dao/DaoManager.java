package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Student;
import edu.calpoly.csc365.example1.entity.Book;
import edu.calpoly.csc365.example1.entity.Checkout;
import edu.calpoly.csc365.example1.entity.Reservation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager{

  protected DataSource dataSource = null;
  protected Connection conn = null;

  public DaoManager(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Connection getConnection() throws SQLException {
    if (this.conn == null) {
      this.conn = this.dataSource.getConnection();
    }
    return this.conn;
  }

  public Connection getTransConnection() throws SQLException {
    if (this.conn == null) {
      this.getConnection().setAutoCommit(false);
    }
    return this.conn;
  }

  public void close() throws SQLException {
    try
    {
      if(this.conn != null && !this.conn.isClosed())
        this.conn.close();
    }
    catch(SQLException e) { throw e; }
  }

  public Object transaction(DaoCommand command){
    try{
      this.conn.setAutoCommit(false);
      Object returnValue = command.execute(this);
      this.conn.commit();
      return returnValue;
    } catch(Exception e){
      try {
        this.conn.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        this.conn.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public Object executeAndClose(DaoCommand command){
    try{
      return command.execute(this);
    } finally {
      try {
        this.getConnection().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Dao<Student> getStudentDao() throws SQLException {
    return new StudentDaoImpl((this.getConnection()));
  }
  public UserDao getUserDao() throws SQLException {
    return new UserDaoImpl(this.getConnection());
  }
  public Dao<Book> getBookDao() throws SQLException {
    return new BookDaoImpl(this.getConnection());
  }
  public Dao<Checkout> getCheckoutDao() throws SQLException {
    return new CheckoutDaoImpl(this.getConnection());
  }
  public Dao<Reservation> getReservationDao() throws SQLException {
    return new ReservationDaoImpl(this.getConnection());
  }
}
