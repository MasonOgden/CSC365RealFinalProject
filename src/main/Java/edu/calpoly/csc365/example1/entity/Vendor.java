package edu.calpoly.csc365.example1.entity;

public class Vendor {
  private Integer id;
  private String name;
  private String address;

  public Vendor() {
    this.id = null;
    this.name = null;
    this.address = null;
  }

  public Vendor(Integer id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
}
