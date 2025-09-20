package com.shelfwise.backend.modules.books.controllers;


import com.shelfwise.backend.modules.books.service.BookCopyService;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyDto;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<BookCopyDto> getBookCopyById(@Valid @PathVariable Long id) {
        BookCopyDto dto = bookCopyService.getBookCopy(id);
        return ResponseEntity.ok().body(dto);

    }


}
