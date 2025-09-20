package com.shelfwise.backend.modules.LibraryPolicy;


import com.shelfwise.backend.modules.auth.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LibraryPolicyDto {
    private Long id;

    private Integer borrowPeriodDays;       // e.g., 14

    private Integer maxBooksPerUser;        // e.g., 5

    private Integer renewalsAllowed;        // e.g., 1

    private Integer reservationHoldHours;   // e.g., 48

    private Integer finePerDay;             // Rs/day (use BigDecimal if you prefer)

    private Integer maxOverDueDays;

    private LocalDateTime effectiveFrom;

    private LocalDateTime effectiveTo;      // null = open-ended/current

    private Long updatedBy;

    private LocalDateTime updatedAt;
}
