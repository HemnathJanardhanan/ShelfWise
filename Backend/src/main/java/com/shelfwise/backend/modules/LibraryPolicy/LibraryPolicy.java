package com.shelfwise.backend.modules.LibraryPolicy;

import com.shelfwise.backend.modules.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryPolicy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Integer borrowPeriodDays;       // e.g., 14

    @Column(nullable = false)
    private Integer maxBooksPerUser;        // e.g., 5

    @Column(nullable = false)
    private Integer renewalsAllowed;        // e.g., 1

    @Column(nullable = false)
    private Integer reservationHoldHours;   // e.g., 48

    @Column(nullable = false)
    private Integer finePerDay;             // Rs/day (use BigDecimal if you prefer)

    // Versioning window
    @Column(nullable = false)
    private LocalDateTime effectiveFrom;

    @Column
    private LocalDateTime effectiveTo;      // null = open-ended/current

    // Audit
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "userId")
    private User updatedBy;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}

