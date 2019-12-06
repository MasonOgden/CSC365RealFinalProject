package edu.calpoly.csc365.example1.entity;

import java.sql.Date;

public class Transaction {
  private Integer id;
  private Integer customerId;
  private Integer cardNumber;
  private Integer vendorId;
  private Date date;
  private Double amount;

  public Transaction() {
    this.id = null;
    this.customerId = null;
    this.cardNumber = null;
    this.date = null;
    this.amount = null;
  }

  public Transaction(Integer id, Integer customerId, Integer cardNumber,
                     Integer vendorId, Date date, Double amount) {
    this.id = id;
    this.customerId = customerId;
    this.cardNumber = cardNumber;
    this.vendorId = vendorId;
    this.date = date;
    this.amount = amount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
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

  public Integer getVendorId() {
    return vendorId;
  }

  public void setVendorId(Integer vendorId) {
    this.vendorId = vendorId;
  }

  public String toString() {
    return "tid: " + id + ", customer: " + customerId + ", card: " + cardNumber
      + ", vendor: " + vendorId + ", date: " + date + ", amount: " + amount;
  }
}
