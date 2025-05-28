package com.david.Finance_Management.entity.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TransactionCategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private TransactionTypeEntity type;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private float amount;
    private LocalDateTime date;
    private String description;
}
