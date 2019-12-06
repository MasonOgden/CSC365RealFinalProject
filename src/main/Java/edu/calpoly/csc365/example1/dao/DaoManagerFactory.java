package edu.calpoly.csc365.example1.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoManagerFactory {
  public static DaoManager createDaoManager() throws NamingException {
    Context initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:comp/env");
    DataSource ds = (DataSource)envContext.lookup("jdbc/cgallahu");
    return new DaoManager(ds);
  }
}
