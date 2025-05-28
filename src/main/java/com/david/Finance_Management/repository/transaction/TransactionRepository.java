package com.david.Finance_Management.repository.transaction;

import com.david.Finance_Management.entity.transaction.TransactionEntity;
import com.david.Finance_Management.entity.transaction.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    // Fetch transactions (1-INCOMES, 2-EXPENSES) for a specific user, ordered by date descending
    List<TransactionEntity> findByUserIdAndTypeIdOrderByDateDesc(UUID userId, int type);
}

