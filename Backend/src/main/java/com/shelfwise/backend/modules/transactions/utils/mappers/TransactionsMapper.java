package com.shelfwise.backend.modules.transactions.utils.mappers;

import com.shelfwise.backend.modules.LibraryPolicy.LibraryPolicyService;
import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.repository.UserRepository;
import com.shelfwise.backend.modules.books.models.BookCopy;
import com.shelfwise.backend.modules.books.repository.BookCopyRepository;
import com.shelfwise.backend.modules.transactions.models.TransactionStatus;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionDto;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionsMapper {

    private final BookCopyRepository bookCopyrepo;
    private final UserRepository userrepo;
    private final LibraryPolicyService libraryService;

    public TransactionsMapper(BookCopyRepository bookCopyrepo, UserRepository userrepo, LibraryPolicyService libraryService) {
        this.bookCopyrepo = bookCopyrepo;
        this.userrepo = userrepo;
        this.libraryService = libraryService;
    }

    public Transactions toTransactions(TransactionRequest req){

        LocalDate now=LocalDate.now();
        LocalDate due=now.plusDays(libraryService.borrowPeriodDays());

        User user=userrepo.findByUserId(req.getUserId()).orElseThrow(()->new EntityNotFoundException("User not found"));
        BookCopy bookCopy = bookCopyrepo.findByBookCopyId(req.getBookCopyId()).orElseThrow(()->new EntityNotFoundException("Book copy not found"));


        Transactions transactions = Transactions.builder()
                .bookCopy(bookCopy)
                .user(user)
                .status(TransactionStatus.BORROWED)
                .checkoutDate(now)
                .dueDate(due)
                .build();
        return transactions;
    }

    public TransactionDto  toTransactionDto(Transactions txn){
        TransactionDto dto= TransactionDto.builder()
                .tId(txn.getTId())
                .bookCopyId(txn.getBookCopy().getBookCopyId())
                .userId(txn.getUser().getUserId())
                .checkoutDate(txn.getCheckoutDate())
                .dueDate(txn.getDueDate())
                .returnDate(txn.getReturnDate())
                .status(txn.getStatus())
                .createdAt(txn.getCreatedAt())
                .updatedAt(txn.getUpdatedAt())
                .build();
        return dto;
    }

}
