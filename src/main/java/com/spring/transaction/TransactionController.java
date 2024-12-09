package com.spring.transaction;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  @Autowired
  private TransactionRepository transactionRepository;

  @GetMapping("/list")
  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }

  @GetMapping("/by-account-id")
  public ResponseEntity<List<Transaction>> findByAccountId(@RequestParam long id) {
    List<Transaction> transactions = transactionRepository.findByAccountId(id);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/by-date")
  public ResponseEntity<List<Transaction>> findByTransactionDate(
      @RequestParam String transactionDate) {
    LocalDate date = LocalDate.parse(transactionDate);
    List<Transaction> transactions = transactionRepository.findByTransactionDate(date);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/by-amount")
  public ResponseEntity<List<Transaction>> findByAmount(@RequestParam double amount) {
    List<Transaction> transactions = transactionRepository.findByAmount(amount);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/by-recipient-sender")
  public ResponseEntity<List<Transaction>> findByRecipientSender(
      @RequestParam String recipientSender) {
    List<Transaction> transactions = transactionRepository.findByRecipientSender(recipientSender);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/by-status")
  public ResponseEntity<List<Transaction>> findByStatus(@RequestParam String status) {
    List<Transaction> transactions = transactionRepository.findByStatus(status);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/by-description")
  public ResponseEntity<List<Transaction>> findByDescription(@RequestParam String keyword) {
    List<Transaction> transactions = transactionRepository.findByDescriptionContaining(keyword);
    return ResponseEntity.ok(transactions);
  }
}
