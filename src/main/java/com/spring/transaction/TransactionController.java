package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> getTransactions(
            @RequestParam(required = false) Long account_id) {
        List<Transaction> transactions;
        if (account_id != null) {
            transactions = transactionRepository.findByAccountId(account_id);
        } else {
            transactions = transactionRepository.findAll();
        }
        return ResponseEntity.ok(transactions);
    }


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

    @GetMapping("/by-type")
    public ResponseEntity<List<Transaction>> findByType(@RequestParam String keyword) {
        List<Transaction> transactions = transactionRepository.findByTypeContaining(keyword);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> filterTransactions(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long account_id
    ) {
        LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null) ? LocalDate.parse(endDate) : null;

        List<Transaction> transactions = transactionRepository.filterTransactions(start, end, status, type, account_id);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody Transaction updatedTransaction) {
        return transactionRepository.findById(id)
                .map(existingTransaction -> {

                    // Allow updates to editable fields only
                    existingTransaction.setAmount(updatedTransaction.getAmount());
                    existingTransaction.setRecipientSender(updatedTransaction.getRecipientSender());
                    existingTransaction.setDescription(updatedTransaction.getDescription());
                    existingTransaction.setType(updatedTransaction.getType());

                    // Reset the status to 'Initiated'
                    existingTransaction.setStatus("Initiated");

                    // Save the updated transaction
                    Transaction savedTransaction = transactionRepository.save(existingTransaction);
                    return ResponseEntity.ok(savedTransaction);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Transaction> cancelTransaction(
            @PathVariable Long id,
            @RequestBody Transaction updatedTransaction) {
        return transactionRepository.findById(id)
                .map(existingTransaction -> {

                    // Allow updates to editable fields only
                    existingTransaction.setAmount(updatedTransaction.getAmount());
                    existingTransaction.setRecipientSender(updatedTransaction.getRecipientSender());
                    existingTransaction.setDescription(updatedTransaction.getDescription());
                    existingTransaction.setType(updatedTransaction.getType());

                    // Reset the status to 'Initiated'
                    existingTransaction.setStatus("Canceled");

                    // Save the updated transaction
                    Transaction savedTransaction = transactionRepository.save(existingTransaction);
                    return ResponseEntity.ok(savedTransaction);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
