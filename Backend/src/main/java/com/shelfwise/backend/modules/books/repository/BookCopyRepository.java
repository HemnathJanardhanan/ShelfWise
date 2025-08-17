package com.shelfwise.backend.modules.books.repository;

import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    boolean existsByBook(Book book);
    int countByBook(Book book);
}
