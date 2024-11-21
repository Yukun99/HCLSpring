package com.spring.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@ResponseBody
public class CustomerController {

  private final CustomerRepository repository;

  @Autowired
  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/list")
  public List<Customer> list() {
    return repository.findAll();
  }

  @GetMapping("/get")
  public Customer getCustomer(@RequestParam long id) {
    return repository.findById(id).orElse(null);
  }
}
