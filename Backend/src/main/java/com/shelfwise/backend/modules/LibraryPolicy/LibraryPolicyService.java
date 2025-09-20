package com.shelfwise.backend.modules.LibraryPolicy;


import org.springframework.stereotype.Service;

@Service
public class LibraryPolicyService {

    private final LibraryPolicyReposiitory repo;
    private final LibraryPolicyMapper mapper;

    public LibraryPolicyService(LibraryPolicyReposiitory repo, LibraryPolicyMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public LibraryPolicy current() {
        return repo.getActiveOrThrow();
    }

    public LibraryPolicyDto makePolicy(LibraryPolicyRequest req){
        LibraryPolicy policy=mapper.toPolicy(req);
        repo.save(policy);
        return  mapper.toPolicyDto(policy);
    }
    public LibraryPolicy getCurrentPolicy(){
        return current();
    }
    public int borrowPeriodDays()      { return current().getBorrowPeriodDays(); }
    public int maxBooksPerUser()       { return current().getMaxBooksPerUser(); }
    public int renewalsAllowed()       { return current().getRenewalsAllowed(); }
    public int reservationHoldHours()  { return current().getReservationHoldHours(); }
    public int finePerDay()            { return current().getFinePerDay(); }
    public int maxOverDueDays()        { return current().getMaxOverDueDays(); }
}
