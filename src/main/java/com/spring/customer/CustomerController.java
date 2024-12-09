package com.spring.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customer")
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

  @PostMapping("/update")
  public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedFields) {
    Optional<Customer> existingCustomerOpt = repository.findById(updatedFields.getId());
    if (existingCustomerOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Customer existingCustomer = existingCustomerOpt.get();

    if (updatedFields.getName() != null) {
      existingCustomer.setName(updatedFields.getName());
    }
    if (updatedFields.getUsername() != null) {
      existingCustomer.setUsername(updatedFields.getUsername());
    }
    if (updatedFields.getEmail() != null) {
      existingCustomer.setEmail(updatedFields.getEmail());
    }

    Customer updatedCustomer = repository.save(existingCustomer);
    return ResponseEntity.ok(updatedCustomer);
  }
}
