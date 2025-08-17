package com.shelfwise.backend.modules.books.utils.dto;

import com.shelfwise.backend.modules.books.models.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class NewBookRequest {
    @NotBlank
    private String title;

    @NotNull
    private String authors;

    private String publisher;

    private LocalDate publishDate;

    private Genre genre;

    private String isbn;

}


