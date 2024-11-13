package com.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

  private final List<Customer> customers;
  private final Map<Integer, Customer> idCustomerMap = new HashMap<>();

  /**
   * Autowired initialising of customers.
   * <p>Sorts customers by id (ascending).
   *
   * @param customers Customer list to be autowired.
   */
  @Autowired
  public CustomerRepository(List<Customer> customers) {
    this.customers = customers;
    Collections.sort(customers);
  }

  /**
   * Initialises values of customers into idCustomerMap.
   */
  @PostConstruct
  public void initCustomerMap() {
    for (Customer customer : customers) {
      idCustomerMap.put(customer.getId(), customer);
    }
  }

  /**
   * Gets list of all customers.
   * <p>Sorted by id (ascending).
   *
   * @return List of all customers.
   */
  public List<Customer> getCustomers() {
    return customers;
  }

  /**
   * Gets customer by id.
   *
   * @param id Id of customer to be fetched.
   * @return Customer with corresponding id.
   */
  public Customer getCustomer(int id) {
    return idCustomerMap.get(id);
  }

  /**
   * Gets string representation of CustomerRepository instance.
   *
   * @return String representation of CustomerRepository instance.
   */
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
