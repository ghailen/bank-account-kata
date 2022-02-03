package com.margo.kataproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    private Long accountId;
    private String customerId;
    private String customerName;
    private Double balance;
    private List<OperationHistory> operationHistory;



}
