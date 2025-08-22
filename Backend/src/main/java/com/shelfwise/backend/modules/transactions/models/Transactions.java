package com.shelfwise.backend.modules.transactions.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tId;

    
    @Column(nullable = false)
    private TransactionStatus status;
}
