package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/by-account-id")
    public ResponseEntity<List<Transaction>> findByAccountId(@RequestParam long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Transaction>> findByTransactionDate(@RequestParam String transactionDate) {
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
    public ResponseEntity<List<Transaction>> findByRecipientSender(@RequestParam String recipientSender) {
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
