package com.shelfwise.backend.modules.books.utils.dto;

import com.shelfwise.backend.modules.books.models.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookCopyRequest {

    @NotNull(message = "Title Should not be Empty")
    private long bookId;

    @NotBlank(message = "Location Should not be Empty")
    private String location;

    @NotNull(message = "Status Should not be Empty")
    private BookStatus status;

}
