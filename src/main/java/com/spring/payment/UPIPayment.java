package com.spring.payment;

public class UPIPayment {
    private long accountId;
    private double amount;
    private String recipientUPI;
    private String description;

    // Getters and Setters
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecipientUPI() {
        return recipientUPI;
    }

    public void setRecipientUPI(String recipientUPI) {
        this.recipientUPI = recipientUPI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

