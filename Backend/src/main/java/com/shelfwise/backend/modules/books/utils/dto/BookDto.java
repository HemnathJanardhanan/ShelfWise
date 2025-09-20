package com.shelfwise.backend.modules.books.utils.dto;

import com.shelfwise.backend.modules.author.Author;
import com.shelfwise.backend.modules.books.models.Genre;
import com.shelfwise.backend.modules.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class BookDto {
    private Long bookId;

    private String title;

    private List<String> authorNames;

    private String publisherName;

    private LocalDate publishDate;

    private Genre genre;

    private String isbn;

}
