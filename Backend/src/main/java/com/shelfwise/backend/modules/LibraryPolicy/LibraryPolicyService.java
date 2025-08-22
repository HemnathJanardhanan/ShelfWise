package com.shelfwise.backend.modules.LibraryPolicy;


import org.springframework.stereotype.Service;

@Service
public class LibraryPolicyService {

    private final LibraryPolicyReposiitory repo;

    public LibraryPolicyService(LibraryPolicyReposiitory repo) {
        this.repo = repo;
    }

    public LibraryPolicy current() {
        return repo.getActiveOrThrow();
    }

    public int borrowPeriodDays()      { return current().getBorrowPeriodDays(); }
    public int maxBooksPerUser()       { return current().getMaxBooksPerUser(); }
    public int renewalsAllowed()       { return current().getRenewalsAllowed(); }
    public int reservationHoldHours()  { return current().getReservationHoldHours(); }
    public int finePerDay()            { return current().getFinePerDay(); }
}
