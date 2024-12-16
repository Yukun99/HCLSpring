package com.spring.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(long accountId); // Assuming Account has accountId as a field
    List<Transaction> findByTransactionDate(LocalDate transactionDate);
    List<Transaction> findByAmount(double amount);
    List<Transaction> findByRecipientSender(String recipientSender);
    List<Transaction> findByStatus(String status);
    List<Transaction> findByDescriptionContaining(String keyword);
    List<Transaction> findByTypeContaining(String keyword);

    @Query("SELECT t FROM Transaction t WHERE " +
            "(:start IS NULL OR t.transactionDate >= :start) AND " +
            "(:end IS NULL OR t.transactionDate <= :end) AND " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:type IS NULL OR t.type = :type) AND " +
            "(:account_id IS NULL OR t.account.id = :account_id)")
    List<Transaction> filterTransactions(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("status") String status,
            @Param("type") String type,
            @Param("account_id") Long accountId
    );



}
