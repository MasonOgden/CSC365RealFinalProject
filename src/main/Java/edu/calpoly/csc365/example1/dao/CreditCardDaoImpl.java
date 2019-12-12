package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.CreditCard;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CreditCardDaoImpl implements Dao<CreditCard> {

  private Connection conn;

  public CreditCardDaoImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public CreditCard getById(int id) {
    CreditCard creditCard = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM CreditCard WHERE card_number = ?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      Set<CreditCard> creditCards = unpackResultSet(resultSet);
      creditCard = (CreditCard) creditCards.toArray()[0];
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return creditCard;
  }

  @Override
  public Set<CreditCard> getAll() {
    Set<CreditCard> creditCards = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM CreditCard");
      resultSet = preparedStatement.executeQuery();
      creditCards = unpackResultSet(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return creditCards;
  }

  @Override
  public Integer insert(CreditCard obj) {
    Integer cardNumber = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement(
        "INSERT INTO CreditCard (type, `limit`, balance, active) VALUES (?, ?, ?, ?)");
      preparedStatement.setString(1, obj.getType());
      preparedStatement.setDouble(2, obj.getLimit());
      preparedStatement.setDouble(3, obj.getBalance());
      preparedStatement.setInt(4, obj.getActive() ? 1 : 0);
      int numRows = preparedStatement.executeUpdate();
      if (numRows == 1) {
        // get generated id
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next())
          cardNumber = resultSet.getInt(1);

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
    return cardNumber;
  }

  @Override
  public Integer update(CreditCard obj) {
    Integer numRows = 0;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = this.conn.prepareStatement(
        "UPDATE CreditCard SET type = ?, `limit` = ?, balance = ?, active = ? WHERE card_number = ?",
        Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, obj.getType());
      preparedStatement.setDouble(2, obj.getLimit());
      preparedStatement.setDouble(3, obj.getBalance());
      preparedStatement.setInt(4, obj.getActive() ? 1 : 0);
      preparedStatement.setInt(5, obj.getCardNumber());
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

  @Override
  public Integer delete(CreditCard obj) {
    Integer numRows = 0;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = this.conn.prepareStatement(
        "DELETE FROM CreditCard WHERE card_number = ?");
      preparedStatement.setInt(1, obj.getCardNumber());
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

  private Set<CreditCard> unpackResultSet(ResultSet rs) throws SQLException {
    Set<CreditCard> creditCards = new HashSet<CreditCard>();

    while(rs.next()) {
      CreditCard creditCard = new CreditCard(
        rs.getInt("card_number"),
        rs.getString("type"),
        rs.getDouble("limit"),
        rs.getDouble("balance"),
        rs.getBoolean("active"));
      creditCards.add(creditCard);
    }
    return creditCards;
  }
}
