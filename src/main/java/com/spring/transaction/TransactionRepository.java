package com.spring.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Custom query to find transactions by account ID
    List<Transaction> findByAccountId(long accountId);
}
