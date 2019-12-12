package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.Customer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDaoImpl implements Dao<Customer> {
  private Connection conn;

  public CustomerDaoImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Customer getById(int id) {
    Customer customer = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM Customer WHERE id=?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      Set<Customer> customers = unpackResultSet(resultSet);
      customer = (Customer)customers.toArray()[0];
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
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
    return customer;
  }

  @Override
  public Set<Customer> getAll() {
    Set<Customer> customers = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM Customer");
      resultSet = preparedStatement.executeQuery();
      customers = unpackResultSet(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
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
    return customers;
  }

  @Override
  public Integer insert(Customer obj) {
    Integer id = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement(
        "INSERT INTO Customer (ssn, name, address, phoneNumber) VALUES (?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, obj.getSsn());
      preparedStatement.setString(2, obj.getName());
      preparedStatement.setString(3, obj.getAddress());
      preparedStatement.setString(4, obj.getPhone());
      int numRows = preparedStatement.executeUpdate();
      if (numRows == 1) {
        // get generated id
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next())
          id = resultSet.getInt(1);

      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
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
  public Integer update(Customer obj) {
    Integer numRows = 0;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = this.conn.prepareStatement(
        "UPDATE Customer SET ssn=?, name=?, address=?, phoneNumber=? WHERE id=?");
      preparedStatement.setString(1, obj.getSsn());
      preparedStatement.setString(2, obj.getName());
      preparedStatement.setString(3, obj.getAddress());
      preparedStatement.setString(4, obj.getPhone());
      preparedStatement.setInt(5, obj.getId());
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      return numRows;
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

  @Override
  public Integer delete(Customer obj) {
    Integer numRows = null;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = this.conn.prepareStatement("DELETE FROM Customer WHERE id = ?");
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

  private Set<Customer> unpackResultSet(ResultSet rs) throws SQLException {
    Set<Customer> customers = new HashSet<Customer>();

    while(rs.next()) {
      Customer customer = new Customer(
        rs.getInt("id"),
        rs.getString("ssn"),
        rs.getString("name"),
        rs.getString("address"),
        rs.getString("phoneNumber"));
      customers.add(customer);
    }
    return customers;
  }
}
