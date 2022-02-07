package com.margo.kataproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Bank account.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount {

    private Long accountId;
    private String customerId;
    private String customerName;
    private Double balance = 0.0;
    private List<OperationHistory> operationHistory = new ArrayList<>();


    /**
     * Add operation history.
     *
     * @param in the in
     */
    public void addOperationHistory(OperationHistory in) {
        operationHistory.add(in);
    }

}
