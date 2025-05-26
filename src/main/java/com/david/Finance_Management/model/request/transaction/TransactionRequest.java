package com.david.Finance_Management.model.request.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionRequest {
    private int category_id; // which category from table 'transaction_categories'
    private float amount;
    private LocalDateTime date;
    private String description;
}
