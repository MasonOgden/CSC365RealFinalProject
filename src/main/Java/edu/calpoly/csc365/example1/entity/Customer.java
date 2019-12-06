package edu.calpoly.csc365.example1.entity;

public class Customer {
  private Integer id;
  private String ssn;
  private String name;
  private String address;
  private String phoneNumber;

  public Customer(){
    this.id = null;
    this.ssn = null;
    this.name = null;
    this.address = null;
    this.phoneNumber = null;
  }

  public Customer(String ssn, String name, String address, String phone) {
    this.id = null;
    this.ssn = ssn;
    this.name = name;
    this.address = address;
    this.phoneNumber = phone;
  }

  public Customer(Integer id, String ssn, String name, String address, String phone) {
    this.id = id;
    this.ssn = ssn;
    this.name = name;
    this.address = address;
    this.phoneNumber = phone;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phoneNumber;
  }

  public void setPhone(String phone) {
    this.phoneNumber = phone;
  }

  @Override
  public String toString() {
    return "id: " + id.toString() + ", ssn: " + ssn.toString() + ", name: " + name
      + ", address: " + address + ", phone: " + phoneNumber;
  }
}
