package com.margo.kataproject.service;

import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.entity.OperationHistory;

import java.util.List;

/**
 * The interface Bank account service.
 */
public interface BankAccountService {

    /**
     * Create account bank account.
     *
     * @param bankAccountPayload the bank account payload
     * @return the bank account
     */
    BankAccount createAccount(BankAccount bankAccountPayload);

    /**
     * Deposit.
     *
     * @param accountId the account id
     * @param amount    the amount
     */
    void deposit(Long accountId, int amount);

    /**
     * Withdraw.
     *
     * @param accountId the account id
     * @param amount    the amount
     */
    void withdraw(Long accountId, int amount);

    /**
     * Check operations history list.
     *
     * @param accountId the account id
     * @return the list
     */
    List<OperationHistory> checkOperationsHistory(Long accountId);

}
