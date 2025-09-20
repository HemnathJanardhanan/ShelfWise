package com.shelfwise.backend.modules.transactions.utils.Dto;
import com.shelfwise.backend.modules.transactions.models.TransactionStatus;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    private Long tId;

    private Long userId;

    private Long bookCopyId;

    private LocalDate checkoutDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private TransactionStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
