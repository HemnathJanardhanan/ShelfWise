package com.shelfwise.backend.modules.transactions.utils.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TransactionRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long bookCopyId;
}
