package com.david.Finance_Management.repository.transaction;

import com.david.Finance_Management.entity.transaction.TransactionCategoryEntity;
import com.david.Finance_Management.entity.transaction.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategoryEntity, Integer> {
    Optional<TransactionCategoryEntity> findByNameAndType(String name, TransactionTypeEntity type);
}

