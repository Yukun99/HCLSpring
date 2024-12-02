package com.spring.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@ResponseBody
public class TransactionController {

    private final TransactionRepository repository;

    @Autowired
    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    // Endpoint to list all transactions
    @GetMapping("/list")
    public List<Transaction> list() {
        return repository.findAll();
    }

    // Endpoint to get a transaction by ID
    @GetMapping("/get")
    public Transaction getTransaction(@RequestParam long id) {
        return repository.findById(id).orElse(null);
    }

    // Endpoint to get all transactions for a specific account ID
    @GetMapping("/byAccount")
    public List<Transaction> getTransactionsByAccount(@RequestParam long accountId) {
        return repository.findByAccountId(accountId);
    }
}
