package com.shelfwise.backend.modules.fine.service;

import com.shelfwise.backend.modules.LibraryPolicy.LibraryPolicyService;
import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.service.UserService;
import com.shelfwise.backend.modules.fine.model.Fine;
import com.shelfwise.backend.modules.fine.repository.FineRepository;
import com.shelfwise.backend.modules.fine.utils.Dto.FineDto;
import com.shelfwise.backend.modules.fine.utils.mapper.FineMapper;
import com.shelfwise.backend.modules.transactions.models.TransactionStatus;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import com.shelfwise.backend.modules.transactions.service.TransactionService;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class FineService {
    private final FineMapper fineMapper;
    private final FineRepository fineRepo;
    private final LibraryPolicyService libraryPolicyService;
    private final UserService userService;
    private final TransactionService txnService;

    public  FineService(FineMapper fineMapper, FineRepository fineRepo,LibraryPolicyService libraryPolicyService ,
                        UserService userService,TransactionService txnService) {
        this.fineMapper = fineMapper;
        this.fineRepo = fineRepo;
        this.libraryPolicyService = libraryPolicyService;
        this.userService = userService;
        this.txnService = txnService;
    }

    public Optional<FineDto> checkAndApplyFine(Transactions txn){

        LocalDate returnDate=txn.getReturnDate();
        LocalDate dueDate=txn.getDueDate();

        if(returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)){
            return Optional.empty();
        }
        long overdueDays= ChronoUnit.DAYS.between(dueDate, returnDate);
        BigDecimal fineAmount = BigDecimal.valueOf(overdueDays)
                .multiply(BigDecimal.valueOf(libraryPolicyService.finePerDay()));

        Fine newFine=Fine.builder()
                .user(txn.getUser())
                .amount(fineAmount)
                .transaction(txn)
                .isPaid(false)
                .issuedDate(LocalDate.now())
                .build();

        fineRepo.save(newFine);
        return Optional.of(fineMapper.toFineDto(newFine));
    }

    public void processOverDueTransactions(){
        LocalDate today=LocalDate.now();
        List<Transactions> txns=txnService.getAllOverDueTxns();

        for(Transactions txn:txns){
            if(ChronoUnit.DAYS.between(txn.getDueDate(),today)>libraryPolicyService.maxOverDueDays()){
                txnService.markTxnLost(txn);
            }else{
                txnService.markTxnOverDue(txn);
            }
        }
    }
}
