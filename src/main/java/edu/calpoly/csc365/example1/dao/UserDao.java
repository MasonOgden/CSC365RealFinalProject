package edu.calpoly.csc365.example1.dao;

import edu.calpoly.csc365.example1.entity.User;

public interface UserDao extends Dao<User> {
  public Boolean authenticate(String name, String pass);
}
