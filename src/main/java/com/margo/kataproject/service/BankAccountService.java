package com.margo.kataproject.service;

import com.margo.kataproject.entity.OperationHistory;

import java.util.List;

public interface BankAccountService {

    void deposit(Long accountId, int amount);

    void withdraw(Long accountId, int amount);

    List<OperationHistory> CheckOperationsHistory(Long accountId);
}
