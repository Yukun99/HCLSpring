package com.spring;

public class Customer implements Comparable<Customer> {

  private final int id;
  private final String name;
  private double balance;

  public Customer(int id, String name, double balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  /**
   * Queries current customer status.
   *
   * @return Current customer status.
   */
  public String query() {
    return String.format("Customer %s with ID %d has $%.2f in account.", name, id, balance);
  }

  /**
   * Deposits specified amount into customer's account.
   *
   * @param amount Amount to be deposited into customer's account.
   * @return Current customer status after depositing amount.
   */
  public String deposit(double amount) {
    if (Double.MAX_VALUE - amount < balance) {
      return String.format("Deposit failed!\nCustomer %s with ID %d has too much money.", name, id);
    }
    this.balance += amount;
    return "Deposit success!\n" + query();
  }

  /**
   * Withdraws specified amount from customer's account.
   *
   * @param amount Amount to be withdrawn from customer's account.
   * @return Current customer status after withdrawing amount.
   */
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
