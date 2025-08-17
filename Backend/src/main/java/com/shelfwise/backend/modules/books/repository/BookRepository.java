package com.shelfwise.backend.modules.books.repository;


import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleIgnoreCase(String title);
    Optional<Book> findByGenre(Genre genre);
    Optional<Book> findByBookId(long bookId);
    boolean existsByTitle(String Title);

    

}
