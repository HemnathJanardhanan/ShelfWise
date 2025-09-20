package com.shelfwise.backend.modules.fine.utils.Dto;

import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FineDto {

    private Long fineId;

    private Long tId;

    private Long userId;

    private BigDecimal amount;

    private boolean isPaid;

    private LocalDate issuedDate;

    private LocalDate paidDate;
}
