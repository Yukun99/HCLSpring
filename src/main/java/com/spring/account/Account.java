package com.spring.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_seq")
  @SequenceGenerator(name = "accounts_seq", sequenceName = "ACCOUNTS_SEQ", allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "balance", columnDefinition = "BINARY_DOUBLE")
  private double balance;

  @Column(name = "withdraw_limit", columnDefinition = "BINARY_DOUBLE")
  private double withdrawLimit;

  @Column(name = "overdraft_limit", columnDefinition = "BINARY_DOUBLE")
  private double overdraftLimit;

  public Account() {
  }

  public Account(double balance, double withdrawLimit, double overdraftLimit) {
    this.balance = balance;
    this.withdrawLimit = withdrawLimit;
    this.overdraftLimit = overdraftLimit;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getWithdrawLimit() {
    return withdrawLimit;
  }

  public void setWithdrawLimit(double withdrawLimit) {
    this.withdrawLimit = withdrawLimit;
  }

  public double getOverdraftLimit() {
    return overdraftLimit;
  }

  public void setOverdraftLimit(double overdraftLimit) {
    this.overdraftLimit = overdraftLimit;
  }
}
