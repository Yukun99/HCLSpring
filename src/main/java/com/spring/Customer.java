package com.spring;

public class Customer {

  private final int id;
  private final String name;
  private double balance;

  public Customer(int id, String name, double balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
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
  public String toString() {
    return "Customer [id=" + id + ", name=" + name + "]";
  }
}
