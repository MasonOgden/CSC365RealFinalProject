package edu.calpoly.csc365.example1.dao;

public interface DaoCommand {
  Object execute(DaoManager daoManager);
}
