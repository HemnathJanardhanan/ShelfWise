package com.shelfwise.backend.modules.books.repository;

import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    Optional<BookCopy> findByBookCopyId(Long bookCopyId);
    boolean existsByBook(Book book);
    int countByBook(Book book);
}
