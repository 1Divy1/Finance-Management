package com.david.Finance_Management.repository.transaction;

import com.david.Finance_Management.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}

