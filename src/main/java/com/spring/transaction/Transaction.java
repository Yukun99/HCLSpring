package com.spring.transaction;

import com.spring.account.Account;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
  @SequenceGenerator(name = "transactions_seq", sequenceName = "TRANSACTIONS_SEQ", allocationSize = 1)
  @Column(name = "transaction_id")
  private long transactionId;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @Column(name = "transaction_date", nullable = false)
  private LocalDate transactionDate;

  @Column(name = "amount", columnDefinition = "BINARY_DOUBLE", nullable = false)
  private double amount;

  @Column(name = "recipient_sender", nullable = false, length = 100)
  private String recipientSender;

  @Column(name = "status", nullable = false, length = 50)
  private String status;

  @Column(name = "description")
  private String description;

  public Transaction() {
  }

  public Transaction(Account account, LocalDate transactionDate, double amount,
      String recipientSender, String status, String description) {
    this.account = account;
    this.transactionDate = transactionDate;
    this.amount = amount;
    this.recipientSender = recipientSender;
    this.status = status;
    this.description = description;
  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDate transactionDate) {
    this.transactionDate = transactionDate;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getRecipientSender() {
    return recipientSender;
  }

  public void setRecipientSender(String recipientSender) {
    this.recipientSender = recipientSender;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}