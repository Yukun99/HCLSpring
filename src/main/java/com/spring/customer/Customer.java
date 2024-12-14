package com.spring.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.account.Account;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
  @SequenceGenerator(name = "customers_seq", sequenceName = "CUSTOMERS_SEQ", allocationSize = 1)
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @OneToOne
  @JoinColumn(name = "account", nullable = false)
  private Account account;

  public Customer() {
  }

  public Customer(String name, String username, String email, String password, Account account) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.account = account;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public String getName() { return name; }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Account getAccount() { return account; }

  public void setAccount(Account account) { this.account = account; }


  @Override
  public String toString() {
    return
        "Customer"
            + " [id=" + id
            + ", name=" + name
            + ", username=" + username
            + ", email=" + email
            + "]";
  }


}
