package com.shelfwise.backend.modules.books.service;


import com.shelfwise.backend.modules.books.models.BookCopy;
import com.shelfwise.backend.modules.books.models.BookStatus;
import com.shelfwise.backend.modules.books.repository.BookCopyRepository;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyDto;
import com.shelfwise.backend.modules.books.utils.dto.BookCopyRequest;
import com.shelfwise.backend.modules.books.utils.mapper.BookCopyMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookCopyService {

    private final BookCopyRepository copyRepo;
    private final BookCopyMapper mapper;

    public BookCopyService(BookCopyRepository bookCopyRepository, BookCopyMapper mapper) {
        this.copyRepo = bookCopyRepository;
        this.mapper = mapper;
    }

    public BookCopyDto addBookCopy(BookCopyRequest req) {
        BookCopy bookCopy = mapper.toBookCopy(req);
        BookCopy bookCopySaved = copyRepo.save(bookCopy);
        return mapper.toBookCopyDto(bookCopySaved);
    }

    public BookCopyDto getBookCopy(Long id) {
        BookCopy bookCopy = copyRepo.findByBookCopyId(id).orElseThrow(()-> new EntityNotFoundException("BookCopy not found"));
        return mapper.toBookCopyDto(bookCopy);
    }

//    public BookCopyDto updateBookCopy() {}

}
