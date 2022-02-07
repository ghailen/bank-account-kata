package com.margo.kataproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * The type Operation history.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory {

    private String operationType;
    private Date date;
    private Double balance;
    private int amount;


}
