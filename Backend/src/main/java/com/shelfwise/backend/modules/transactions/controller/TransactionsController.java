package com.shelfwise.backend.modules.transactions.controller;


import com.shelfwise.backend.modules.transactions.service.TransactionService;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionDto;
import com.shelfwise.backend.modules.transactions.utils.Dto.TransactionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/txn")
public class TransactionsController {
    private final TransactionService txnService;
    public  TransactionsController(TransactionService transactionService) {
        this.txnService = transactionService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<TransactionDto> borrowBook(@Valid @RequestBody TransactionRequest req) {
        TransactionDto txnDto=txnService.lendBook(req);
        return ResponseEntity.ok(txnDto);
    }

    @PutMapping("/return")
    public ResponseEntity<TransactionDto> returnBook(@Valid @RequestBody TransactionRequest req) {
        TransactionDto txnDto=txnService.returnBook(req);
        return ResponseEntity.ok(txnDto);
    }


}
