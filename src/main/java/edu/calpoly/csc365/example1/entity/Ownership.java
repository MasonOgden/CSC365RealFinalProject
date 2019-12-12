package edu.calpoly.csc365.example1.entity;

public class Ownership {
  private Integer ownerId;
  private Integer cardNumber;
  private Boolean current;

  public Ownership() {
    this.ownerId = null;
    this.cardNumber = null;
    this.current = null;
  }

  public Ownership(Integer ownerId, Integer cardNumber, Boolean current) {
    this.ownerId = ownerId;
    this.cardNumber = cardNumber;
    this.current = current;
  }

  public Integer getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Integer ownerId) {
    this.ownerId = ownerId;
  }

  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Boolean getCurrent() {
    return current;
  }

  public void setCurrent(Boolean current) {
    this.current = current;
  }
}
