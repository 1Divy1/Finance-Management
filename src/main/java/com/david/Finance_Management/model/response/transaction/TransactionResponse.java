package com.david.Finance_Management.model.response.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
    private int type; // 1 for INCOME, 2 for EXPENSE
    private int category_id; // which category from table 'transaction_categories'
    private float amount;
    private String description;
    private LocalDateTime date;
}
