package com.zakiyama.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data // constructors, toString, hash, equals, and other things.
@Entity // is a JPA annotation that denotes the whole class for storage in a relational table.
public class Employee {

  // @Id and @GeneratedValue are JPA annotations to note the primary key and that is generated automatically when needed.
  private @Id @GeneratedValue Long id;
  private String firstName;
  private String lastName;
  private String description;

  private Employee() {}

  public Employee(String firstName, String lastName, String description) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
  }
}