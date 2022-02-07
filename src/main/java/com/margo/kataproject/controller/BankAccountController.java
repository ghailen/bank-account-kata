package com.margo.kataproject.controller;

import com.margo.kataproject.dto.TransactionOperation;
import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The type Bank account controller.
 */
@RestController
@RequestMapping(value = "/api/accounting")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    /**
     * Instantiates a new Bank account controller.
     *
     * @param bankAccountService the bank account service
     */
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * Create account response entity.
     *
     * @param in the in
     * @return the response entity
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@RequestBody BankAccount in) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.createAccount(in));
    }

    /**
     * Deposit response entity.
     *
     * @param in the in
     * @return the response entity
     */
    @PostMapping("/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionOperation in) {
        bankAccountService.deposit(in.getAccountId(), in.getAmount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    /**
     * Withdraw response entity.
     *
     * @param in the in
     * @return the response entity
     */
    @GetMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody TransactionOperation in) {
        bankAccountService.withdraw(in.getAccountId(), in.getAmount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    /**
     * Check operations history response entity.
     *
     * @param accountId the account id
     * @return the response entity
     */
    @GetMapping("/operations-history/{accountId}")
    public ResponseEntity<Object> checkOperationsHistory(@PathVariable Long accountId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bankAccountService.checkOperationsHistory(accountId));

    }
}
