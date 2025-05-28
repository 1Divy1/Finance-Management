package com.david.Finance_Management.service;

import com.david.Finance_Management.entity.transaction.TransactionCategoryEntity;
import com.david.Finance_Management.entity.transaction.TransactionEntity;
import com.david.Finance_Management.entity.transaction.TransactionTypeEntity;
import com.david.Finance_Management.model.request.transaction.TransactionRequest;
import com.david.Finance_Management.model.response.transaction.TransactionResponse;
import com.david.Finance_Management.repository.transaction.TransactionCategoryRepository;
import com.david.Finance_Management.repository.transaction.TransactionRepository;
import com.david.Finance_Management.repository.transaction.TransactionTypeRepository;
import com.david.Finance_Management.security.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo; // handles main transactions

    @Autowired
    private TransactionCategoryRepository categoryRepo; // handles transaction categories (food, transport, etc.)

    @Autowired
    private TransactionTypeRepository typeRepo; // handles transaction types (income, expense)

    @Autowired
    private AuthUtils authUtils; // utility class for authentication-related operations (currently used to get user's ID)

    /**
     * Adds a new transaction (INCOME or EXPENSE)
     *
     * @param transactionRequest the request containing transaction details
     * @return the response containing the DTO transaction details
     */
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {

        // checks if transaction category ("FOOD", "TRANSPORT", "SALARY", etc.) exists
        TransactionCategoryEntity category = categoryRepo
                .findById(transactionRequest.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Transaction category not found"));


        // check if main transaction type (INCOME or EXPENSE) exists
        TransactionTypeEntity type = typeRepo
                .findById(transactionRequest.getType())
                .orElseThrow(() -> new RuntimeException("Transaction type not found"));


        // get the current user's ID from the authentication context
        UUID userId = authUtils.getCurrentUserId();

        TransactionEntity transaction = new TransactionEntity();
        transaction.setUserId(userId);
        transaction.setDate(transactionRequest.getDate());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setType(type);
        transaction.setCategory(category);

        // TODO: in the future, add validation to avoid sending an income transaction with an expense category
        transactionRepo.save(transaction);

        return mapToResponse(transaction);
    }

    /**
     * Fetches all transactions of a certain type for the current user.
     *
     * @param transactionType the type of transaction (1 for INCOME, 2 for EXPENSE)
     * @return a list of transaction responses
     */
    public List<TransactionResponse> getTransactions(int transactionType) {

        // get the current user's ID from the authentication context
        UUID userId = authUtils.getCurrentUserId();

        // check if main transaction type (INCOME or EXPENSE) exists
        TransactionTypeEntity type = typeRepo
                .findById(transactionType)
                .orElseThrow(() -> new RuntimeException("Transaction type not found"));

        // fetch all transactions for the user, descending order by date
        List<TransactionEntity> incomes = transactionRepo.findByUserIdAndTypeIdOrderByDateDesc(userId, transactionType);

        return incomes.stream().map(this::mapToResponse).toList();
    }

    /**
     * Maps a TransactionEntity to a TransactionResponse.
     *
     * @param transaction the transaction entity to map
     * @return the mapped transaction response
     */
    private TransactionResponse mapToResponse(TransactionEntity transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setType(transaction.getType().getId());
        response.setDate(transaction.getDate());
        response.setAmount(transaction.getAmount());
        response.setDescription(transaction.getDescription());
        response.setCategory_id(transaction.getCategory().getId());

        return response;
    }
}
