package edu.calpoly.csc365.example1.entity;

import java.sql.Date;

public class Payment {
  private Integer id;
  private Integer cardNumber;
  private Date date;
  private Double amount;

  public Payment() {
    this.id = null;
    this.cardNumber = null;
    this.date = null;
    this.amount = null;
  }

  public Payment(Integer id, Integer cardNumber, Date date, Double amount) {
    this.id = id;
    this.cardNumber = cardNumber;
    this.date = date;
    this.amount = amount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
