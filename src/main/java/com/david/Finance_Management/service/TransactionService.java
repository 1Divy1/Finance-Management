package com.david.Finance_Management.service;

import com.david.Finance_Management.entity.transaction.TransactionCategoryEntity;
import com.david.Finance_Management.entity.transaction.TransactionEntity;
import com.david.Finance_Management.model.request.transaction.TransactionRequest;
import com.david.Finance_Management.model.response.transaction.TransactionResponse;
import com.david.Finance_Management.repository.transaction.TransactionCategoryRepository;
import com.david.Finance_Management.repository.transaction.TransactionRepository;
import com.david.Finance_Management.repository.transaction.TransactionTypeRepository;
import com.david.Finance_Management.security.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    // handles main transactions
    @Autowired
    private TransactionRepository transactionRepo;

    // handles transaction categories (food, transport, etc.)
    @Autowired
    private TransactionCategoryRepository categoryRepo;

    // handles transaction types (income, expense)
    @Autowired
    private TransactionTypeRepository typeRepo;

    // utility class for authentication-related operations (currently used to get user's ID)
    @Autowired
    private AuthUtils authUtils;

    /**
     * Adds a new income transaction.
     *
     * @param transactionRequest the request containing transaction details
     * @return the response containing the DTO transaction details
     */
    public TransactionResponse addIncome(TransactionRequest transactionRequest) {

        // fetch the transaction category based on the provided category ID
        TransactionCategoryEntity category = categoryRepo
                .findById(transactionRequest.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // get the current user's ID from the authentication context
        UUID userId = authUtils.getCurrentUserId();

        TransactionEntity transaction = new TransactionEntity();
        transaction.setUser_id(userId);
        transaction.setDate(transactionRequest.getDate());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setCategory(category);

        transactionRepo.save(transaction);
        return mapToResponse(transaction);
    }

    /**
     * Maps a TransactionEntity to a TransactionResponse.
     *
     * @param transaction the transaction entity to map
     * @return the mapped transaction response
     */
    private TransactionResponse mapToResponse(TransactionEntity transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setDate(transaction.getDate());
        response.setAmount(transaction.getAmount());
        response.setDescription(transaction.getDescription());
        response.setCategory_id(transaction.getCategory().getId());
        return response;
    }

//    public List<TransactionResponse> getAllTransactionCategories() {
//
//    }
}
