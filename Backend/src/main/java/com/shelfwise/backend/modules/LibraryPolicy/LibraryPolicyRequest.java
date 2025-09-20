package com.shelfwise.backend.modules.LibraryPolicy;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class LibraryPolicyRequest {

    private Integer borrowPeriodDays;       // e.g., 14

    private Integer maxBooksPerUser;        // e.g., 5

    private Integer renewalsAllowed;        // e.g., 1

    private Integer reservationHoldHours;   // e.g., 48

    private Integer finePerDay;             // Rs/day (use BigDecimal if you prefer)

    private Integer maxOverDueDays;

    private LocalDateTime effectiveFrom;

    private LocalDateTime effectiveTo;      // null = open-ended/current

    private Long updatedBy;
}
