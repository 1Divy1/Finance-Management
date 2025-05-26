package com.david.Finance_Management.entity.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "transaction_category",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "type_id"})}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TransactionTypeEntity type;

    private String name;
}
