package com.spring;

public class Customer implements Comparable<Customer> {

  private int id;
  private String name;
  private double balance;

  public Customer() {
  }

  public Customer(int id, String name, double balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getBalance() {
    return balance;
  }

  public String query() {
    return String.format("Customer %s with ID %d has $%.2f in account.", name, id, balance);
  }

  public String deposit(double amount) {
    if (Double.MAX_VALUE - amount < balance) {
      return String.format("Deposit failed!\nCustomer %s with ID %d has too much money.", name, id);
    }
    this.balance += amount;
    return "Deposit success!\n" + query();
  }

  public String withdraw(double amount) {
    if (amount > balance) {
      return String.format("Withdraw failed!\nCustomer %s with ID %d has no money.", name, id);
    }
    this.balance -= amount;
    return "Withdraw success!\n" + query();
  }

  @Override
  public int compareTo(Customer o) {
    return this.id - o.id;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", name=" + name + "]";
  }
}
