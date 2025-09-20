package com.shelfwise.backend.modules.LibraryPolicy;

import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LibraryPolicyMapper {
    private final UserService userService;
    public LibraryPolicyMapper(UserService userService) {
        this.userService = userService;
    }


    public LibraryPolicyDto toPolicyDto(LibraryPolicy libraryPolicy){
        return LibraryPolicyDto.builder()
                .effectiveFrom(libraryPolicy.getEffectiveFrom())
                .borrowPeriodDays(libraryPolicy.getBorrowPeriodDays())
                .effectiveTo(libraryPolicy.getEffectiveTo())
                .maxBooksPerUser(libraryPolicy.getMaxBooksPerUser())
                .maxOverDueDays(libraryPolicy.getMaxOverDueDays())
                .renewalsAllowed(libraryPolicy.getRenewalsAllowed())
                .reservationHoldHours(libraryPolicy.getReservationHoldHours())
                .updatedAt(libraryPolicy.getUpdatedAt())
                .updatedBy(libraryPolicy.getUpdatedBy().getUserId())
                .finePerDay(libraryPolicy.getFinePerDay())
                .build();
    }

    public LibraryPolicy toPolicy(LibraryPolicyRequest req){

        User user=userService.getUserObject(req.getUpdatedBy());
        return LibraryPolicy.builder()
                .effectiveFrom(req.getEffectiveFrom())
                .borrowPeriodDays(req.getBorrowPeriodDays())
                .effectiveTo(req.getEffectiveTo())
                .maxBooksPerUser(req.getMaxBooksPerUser())
                .maxOverDueDays(req.getMaxOverDueDays())
                .renewalsAllowed(req.getRenewalsAllowed())
                .finePerDay(req.getFinePerDay())
                .reservationHoldHours(req.getReservationHoldHours())
                .updatedBy(user)
                .build();

    }
}
