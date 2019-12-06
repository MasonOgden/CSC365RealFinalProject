package edu.calpoly.csc365.example1.entity;

public class CreditCard {
  private Integer cardNumber;
  private String type;
  private Double limit;
  private Double balance;
  private Boolean active;

  public enum CardType {
    Visa, Master_Card, American_Express, Discover;
  }

  public CreditCard() {
    this.cardNumber = null;
    this.type = null;
    this.limit = null;
    this.balance = null;
    this.active = null;
  }

  public CreditCard(Integer cardNumber, String type, Double limit, Double balance, Boolean active) {
    this.cardNumber = cardNumber;
    this.type = type;
    this.limit = limit;
    this.balance = balance;
    this.active = active;
  }

  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getLimit() {
    return limit;
  }

  public void setLimit(Double limit) {
    this.limit = limit;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
