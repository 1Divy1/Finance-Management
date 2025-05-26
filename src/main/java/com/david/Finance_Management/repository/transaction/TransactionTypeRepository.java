package com.david.Finance_Management.repository.transaction;

import com.david.Finance_Management.entity.transaction.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Integer> {
    Optional<TransactionTypeEntity> findByName(String name);
}

