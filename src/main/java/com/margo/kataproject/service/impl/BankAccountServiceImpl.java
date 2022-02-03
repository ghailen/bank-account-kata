package com.margo.kataproject.service.impl;

import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.entity.OperationHistory;
import com.margo.kataproject.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {


    private static Map<Long, BankAccount> bankAccounts = new HashMap<>();
    private static Long INDEX_OPERATION_HISTORY = 1L;
    /**
     * The Operation histories.
     */
    List<OperationHistory> operationHistories = new ArrayList<>();

    static {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 2000.0, null);
        BankAccount bankAccount2 = new BankAccount(2L, "09828078", "BenMarzouk", 3000.0, null);
        bankAccounts.put(1L, bankAccount);
        bankAccounts.put(2L, bankAccount2);
    }

    /**
     * Gets all bank accounts.
     *
     * @return the all bank accounts
     */
    public static List<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(bankAccounts.values());
    }

    /**
     * Gets bank account details.
     *
     * @param accountId the account id
     * @return the bank account details
     */
    public static BankAccount getBankAccountDetails(Long accountId) {
        return bankAccounts.get(accountId);
    }

    @Override
    public void deposit(Long accountId, int amount) {
        INDEX_OPERATION_HISTORY += 1;
        BankAccount bankAccount = getBankAccountDetails(accountId);

        if (amount > 0) {
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            OperationHistory operationHistory = new OperationHistory(INDEX_OPERATION_HISTORY, "Deposit", new Date(), bankAccount.getBalance(), amount);
            operationHistories.add(operationHistory);
            System.out.println("Bank account balance after deposit :" + bankAccount.getBalance());
        } else throw new IllegalArgumentException("Enter a valid amount");
        bankAccount.setOperationHistory(operationHistories);
    }

    @Override
    public void withdraw(Long accountId, int amount) {
        INDEX_OPERATION_HISTORY += 1;
        BankAccount bankAccount = getBankAccountDetails(accountId);
        if (amount > 0 && amount <= bankAccount.getBalance()) {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            OperationHistory operationHistory = new OperationHistory(INDEX_OPERATION_HISTORY, "Withdraw", new Date(), bankAccount.getBalance(), amount);
            operationHistories.add(operationHistory);
            System.out.println("Bank account balance after withdraw :" + bankAccount.getBalance());
        } else throw new IllegalArgumentException("Insufficient balance");
        bankAccount.setOperationHistory(operationHistories);
    }

    @Override
    public List<OperationHistory> CheckOperationsHistory(Long accountId) {
        return getBankAccountDetails(accountId).getOperationHistory();
    }


}
