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
@RequestMapping("/api/v1/user/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

//    @GetMapping("/get-all-transaction-categories")
//    public ResponseEntity<List<TransactionResponse>> getAllTransactionCategories() {
//        return ResponseEntity.ok(transactionService.getAllTransactionCategories());
//    }

    /**
     * Endpoint to add a new income transaction.
     *
     * @param transactionRequest the request containing transaction details
     * @return ResponseEntity with the created transaction response
     */
    @PostMapping("/income")
    public ResponseEntity<TransactionResponse> addIncome(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse response = transactionService.addIncome(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
