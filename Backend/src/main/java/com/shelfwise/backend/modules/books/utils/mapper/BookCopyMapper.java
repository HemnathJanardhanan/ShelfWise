package com.shelfwise.backend.modules.books.utils.mapper;


import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.models.BookCopy;
import com.shelfwise.backend.modules.books.repository.BookRepository;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyDto;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class BookCopyMapper {

    private final BookRepository bookRepo;

    public BookCopyMapper(BookRepository bookRepository) {
        this.bookRepo = bookRepository;
    }


    public BookCopyDto toBookCopyDto(BookCopy bookCopy) {
        BookCopyDto bookCopyDto = BookCopyDto.builder()
                .bookCopyId(bookCopy.getBookCopyId())
                .bookId(bookCopy.getBook().getBookId())
                .location(bookCopy.getLocation())
                .status(bookCopy.getStatus())
                .build();
        return bookCopyDto;
    }
   public BookCopy toBookCopy(BookCopyRequest req) {
        Book book=bookRepo.findByBookId(req.getBookId()).orElseThrow(()->new EntityNotFoundException("Book not found"));

        BookCopy bookCopy = BookCopy.builder()
                .book(book)
                .location(req.getLocation())
                .status(req.getStatus())
                .build();
        return bookCopy;
   }

}
