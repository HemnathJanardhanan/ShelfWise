package com.shelfwise.backend.modules.fine.model;


import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fine {
//    Fields: fine_id (PK), transaction_id (FK → Transaction), user_id (FK → User), amount, is_paid (boolean), issued_date, paid_date.
//            Relationships:
//    One Fine → One Transaction.
//    One Fine → One User.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    @OneToOne
    @JoinColumn(name="tId")
    private Transactions transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column
    private boolean isPaid=false;

    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column(nullable = true)
    private LocalDate paidDate;

}
