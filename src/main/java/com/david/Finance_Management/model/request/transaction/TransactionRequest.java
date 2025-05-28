package com.david.Finance_Management.model.request.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionRequest {
    private int type; // 1 for INCOME, 2 for EXPENSE
    private int category_id; // subcategories like: "Food", "Transport", etc.
    private float amount;
    private LocalDateTime date;
    private String description;
}
