package com.shelfwise.backend.modules.transactions.service;


import com.shelfwise.backend.common.exception.BorrowLimitExceededException;
import com.shelfwise.backend.common.exception.UnpaidFineException;
import com.shelfwise.backend.modules.LibraryPolicy.LibraryPolicy;
import com.shelfwise.backend.modules.LibraryPolicy.LibraryPolicyReposiitory;
import com.shelfwise.backend.modules.LibraryPolicy.LibraryPolicyService;
import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.repository.UserRepository;
import com.shelfwise.backend.modules.books.models.BookCopy;
import com.shelfwise.backend.modules.books.models.BookStatus;
import com.shelfwise.backend.modules.books.repository.BookCopyRepository;
import com.shelfwise.backend.modules.books.repository.BookRepository;
import com.shelfwise.backend.modules.fine.model.Fine;
import com.shelfwise.backend.modules.fine.repository.FineRepository;
import com.shelfwise.backend.modules.fine.service.FineService;
import com.shelfwise.backend.modules.fine.utils.Dto.FineDto;
import com.shelfwise.backend.modules.transactions.models.TransactionStatus;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import com.shelfwise.backend.modules.transactions.repository.TransactionsRepository;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionDto;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionRequest;
import com.shelfwise.backend.modules.transactions.utils.mappers.TransactionsMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionsRepository txnrepo;
    private  final TransactionsMapper mapper;
    private final UserRepository userRepo;
    private final FineRepository fineRepo;
    private final LibraryPolicyService libraryPolicy;
    private final BookCopyRepository bookCopyRepo;
    private final FineService fineService;

    public TransactionService(TransactionsRepository repo, TransactionsMapper mapper,
                              UserRepository userRepo, FineRepository fineRepo, LibraryPolicyService libraryPolicy,
                              BookCopyRepository bookRepo,FineService fineService) {
        this.txnrepo = repo;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.fineRepo = fineRepo;
        this.libraryPolicy = libraryPolicy;
        this.bookCopyRepo = bookRepo;
        this.fineService = fineService;
    }


    @Transactional
    public TransactionDto lendBook(TransactionRequest req){
        User user=userRepo.findByUserId(req.getUserId()).orElseThrow(()->new EntityNotFoundException("User not found"));
        if(fineRepo.countByUserUserIdAndIsPaid(user.getUserId(),false)>0){
            throw new UnpaidFineException("User has Unpaid Fines");
        }
        if (txnrepo.countByUserUserIdAndStatus(user.getUserId(), TransactionStatus.BORROWED)>= libraryPolicy.maxBooksPerUser()){
            throw new BorrowLimitExceededException("Borrow Limit Exceeded");
        }

        BookCopy bc=bookCopyRepo.findByBookCopyId(req.getBookCopyId()).orElseThrow(()->new EntityNotFoundException("Book Copy Not Found"));
        Transactions txn= mapper.toTransactions(req);

        bc.setStatus(BookStatus.CHECKED_OUT);
        bookCopyRepo.save(bc);
        txnrepo.save(txn);
        return mapper.toTransactionDto(txn);

    }

    @Transactional
    public TransactionDto returnBook(TransactionRequest req){

        Transactions txn=txnrepo.findByUserUserIdAndBookCopyBookCopyIdAndStatus(req.getUserId(),req.getBookCopyId(),TransactionStatus.BORROWED).orElseThrow(()->new EntityNotFoundException("Invalid Transaction"));
        BookCopy bc=bookCopyRepo.findByBookCopyId(req.getBookCopyId()).orElseThrow(()->new EntityNotFoundException("Book Copy Not Found"));

        txn.setStatus(TransactionStatus.RETURNED);
        txn.setReturnDate(LocalDate.now());
        bc.setStatus(BookStatus.AVAILABLE);
        bookCopyRepo.save(bc);
        txnrepo.save(txn);

        Optional<FineDto> fine=fineService.checkAndApplyFine(txn);

        return mapper.toTransactionDto(txn);

    }

    public List<Transactions> getAllOverDueTxns(){
        LocalDate today=LocalDate.now();
        return txnrepo.findByStatusAndDueDateBefore(TransactionStatus.BORROWED,today);
    }

    @Transactional
    public void markTxnLost(Transactions txn){
        txn.getBookCopy().setStatus(BookStatus.LOST);
        txn.setStatus(TransactionStatus.LOST);
        txnrepo.save(txn);
    }

    public void markTxnOverDue(Transactions txn){
        txn.setStatus(TransactionStatus.OVERDUE);
        txnrepo.save(txn);
    }

}
