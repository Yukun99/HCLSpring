package com.spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepository {

  @Autowired
  private List<Customer> customers;

  public Customer getCustomer(int id) {
    return customers.get(id - 1);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CustomerRepository:");
    for (Customer customer : customers) {
      builder.append(customer.toString()).append("\n");
    }
    return builder.toString();
  }
}
