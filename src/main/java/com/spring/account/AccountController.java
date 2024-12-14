package com.spring.account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/api/account")
public class AccountController {

  private final AccountRepository repository;

  @Autowired
  public AccountController(AccountRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/list")
  public List<Account> list() {
    return repository.findAll();
  }

  @GetMapping("/get")
  public Account getAccount(@RequestParam long id) {
    return repository.findById(id).orElse(null);
  }
}
