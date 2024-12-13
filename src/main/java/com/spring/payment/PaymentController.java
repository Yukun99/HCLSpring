package com.spring.payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.spring.account.Account;
import com.spring.account.AccountRepository;
import com.spring.customer.Customer;
import com.spring.transaction.Transaction;
import com.spring.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Long.parseLong;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accountID")
    public Account getAccountId(@RequestParam long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @PostMapping("/upiPayment")
    public ResponseEntity<String> processUPIPayment(@RequestBody UPIPayment paymentRequest) {

        Account userAccount = accountRepository.findById(paymentRequest.getAccountId()).orElse(null);
        if (userAccount == null) {
            return ResponseEntity.badRequest().body("Invalid account ID");
        }

        if (paymentRequest.getAmount() > userAccount.getBalance()) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        long receiveId = parseLong(paymentRequest.getRecipientUPI());

        Account recipient = accountRepository.findById(receiveId).orElse(null);

        //Optional<Account> recipient = accountRepository.findById(parseLong(paymentRequest.getRecipientUPI()));
        if (recipient == null) {
            return ResponseEntity.badRequest().body("Invalid recipient ID");
        }



        userAccount.setBalance(userAccount.getBalance() - paymentRequest.getAmount());
        accountRepository.save(userAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(userAccount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmount(paymentRequest.getAmount());
        transaction.setRecipientSender(paymentRequest.getRecipientUPI());
        transaction.setStatus("Completed");
        transaction.setDescription(paymentRequest.getDescription());
        transaction.setType("UPIPayment");
        transactionRepository.save(transaction);

        return ResponseEntity.ok("UPI payment successful");
    }

    @PostMapping("/bank-transfer")
    public ResponseEntity<String> transfer(@RequestBody BankTransfer bankTransferRequest) {

        Account senderAccount = accountRepository.findById(bankTransferRequest.getSenderAccountId()).orElse(null);
        Account recipientAccount = accountRepository.findById(bankTransferRequest.getRecipientAccountId()).orElse(null);

        if (recipientAccount == null) {
            return ResponseEntity.badRequest().body("Invalid Recipient Account ID");
        }

        assert senderAccount != null;
        if (bankTransferRequest.getAmount() > senderAccount.getBalance()) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        senderAccount.setBalance(senderAccount.getBalance() - bankTransferRequest.getAmount());
        accountRepository.save(senderAccount);

        recipientAccount.setBalance(recipientAccount.getBalance() + bankTransferRequest.getAmount());
        accountRepository.save(recipientAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(senderAccount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmount(bankTransferRequest.getAmount());
        transaction.setRecipientSender(Long.toString(bankTransferRequest.getRecipientAccountId()));
        transaction.setStatus("Completed");
        transaction.setDescription(bankTransferRequest.getDescription());
        transaction.setType("Bank-Transfer");
        transactionRepository.save(transaction);

        return ResponseEntity.ok("Bank Transfer successful");


    }
}
