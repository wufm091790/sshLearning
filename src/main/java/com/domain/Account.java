package com.domain;

import javax.persistence.*;

@Entity
@Table(name="account", catalog="spring")
public class Account {

  private long id;
  private String name;
  private double money;

  @Id
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

}
