package com.scottfree.datapump.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class DBSubscriber implements Serializable {

  private @Id @GeneratedValue long id;
  private java.sql.Timestamp thetime;
  private String data;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getThetime() {
    return thetime;
  }

  public void setThetime(java.sql.Timestamp thetime) {
    this.thetime = thetime;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

}
