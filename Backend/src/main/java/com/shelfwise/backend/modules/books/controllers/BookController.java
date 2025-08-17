package com.shelfwise.backend.modules.books.controllers;

import com.shelfwise.backend.modules.books.service.BookService;
import com.shelfwise.backend.modules.books.utils.dto.BookDto;
import com.shelfwise.backend.modules.books.utils.dto.NewBookRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books=service.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        BookDto book=service.getBookById(id);
        if(book!=null){
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

//    Add Book
    @PostMapping("/")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody NewBookRequest req) {
        BookDto book=service.addBook(req);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable long id, @Valid @RequestBody NewBookRequest req) {
        BookDto book=service.updateBook(id, req);
        if(book!=null){
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable long id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
