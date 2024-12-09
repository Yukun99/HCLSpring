package com.spring.transaction;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByAccountId(long id);

  List<Transaction> findByTransactionDate(LocalDate transactionDate);

  List<Transaction> findByAmount(double amount);

  List<Transaction> findByRecipientSender(String recipientSender);

  List<Transaction> findByStatus(String status);

  List<Transaction> findByDescriptionContaining(String keyword);
}
