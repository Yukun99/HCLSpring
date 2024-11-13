package com.spring;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

  private final CustomerRepository repository;

  @Autowired
  public OrderController(CustomerRepository repository) {
    this.repository = repository;
  }

  @RequestMapping("/start")
  @ResponseBody
  public String startController() {
    return "Controller started!";
  }

  @RequestMapping("/get")
  @ResponseBody
  public List<String> getCustomers() {
    List<String> result = new ArrayList<>();
    for (Customer customer : repository.getCustomers()) {
      result.add(customer.query());
    }
    return result;
  }

  @RequestMapping("/query/{id}")
  @ResponseBody
  public String queryCustomer(@PathVariable int id) {
    return repository.getCustomer(id).query();
  }

  @RequestMapping("/deposit/{id}")
  @ResponseBody
  public String depositCustomer(@PathVariable int id, @RequestBody double amount) {
    return repository.getCustomer(id).deposit(amount);
  }

  @RequestMapping("/withdraw/{id}")
  @ResponseBody
  public String withdrawCustomer(@PathVariable int id, @RequestBody double amount) {
    return repository.getCustomer(id).withdraw(amount);
  }
}
