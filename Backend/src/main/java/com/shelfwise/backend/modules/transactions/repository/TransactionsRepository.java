package com.shelfwise.backend.modules.transactions.repository;

import com.shelfwise.backend.modules.transactions.models.TransactionStatus;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByUserUserIdAndStatus(Long userId);

    Optional<Transactions> findByUserUserIdAndBookCopyBookCopyIdAndStatus(Long userId, Long bookCopyId, TransactionStatus status);

    List<Transactions> findByStatusAndDueDateBefore(TransactionStatus status, LocalDate date);


    int countByUserUserIdAndStatus(Long userId, TransactionStatus status);
}
