package com.shelfwise.backend.modules.books.controllers;


import com.shelfwise.backend.modules.books.service.BookCopyService;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyDto;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookcopy")
public class BookCopyController {

    private final BookCopyService bookCopyService;

    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping("/")
    public ResponseEntity<BookCopyDto> addBookCopy(@Valid @RequestBody BookCopyRequest req) {
        BookCopyDto dto = bookCopyService.addBookCopy(req);
        return ResponseEntity.ok().body(dto);

    }
}
