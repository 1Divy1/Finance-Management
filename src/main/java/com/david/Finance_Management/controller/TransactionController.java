package com.david.Finance_Management.controller;

import com.david.Finance_Management.model.request.transaction.TransactionRequest;
import com.david.Finance_Management.model.response.transaction.TransactionResponse;
import com.david.Finance_Management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Add a new income transaction.
     *
     * @param transactionRequest the request containing transaction details
     * @return ResponseEntity with the created transaction response
     */
    @PostMapping("/incomes")
    public ResponseEntity<TransactionResponse> addIncome(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse response = transactionService.addTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Get all transactions for a specific transaction type (INCOME or EXPENSE).
     * There's no need for a dedicated endpoint since both INCOME and EXPENSE are fetched using the same service & repository methods.
     *
     * @param transactionType the type of transaction (1 for INCOME, 2 for EXPENSE)
     * @return ResponseEntity with a list of income transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam int transactionType) {
        List<TransactionResponse> incomes = transactionService.getTransactions(transactionType);
        return ResponseEntity.status(HttpStatus.OK).body(incomes);
    }

    /**
     * Add a new expense transaction.
     *
     * @param transactionRequest the request containing transaction details
     * @return ResponseEntity with the created transaction response
     */
    @PostMapping("/expenses")
    public ResponseEntity<TransactionResponse> addExpense(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse response = transactionService.addTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
