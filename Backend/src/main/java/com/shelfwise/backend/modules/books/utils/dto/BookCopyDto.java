package com.shelfwise.backend.modules.books.utils.dto;

import com.shelfwise.backend.modules.books.models.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopyDto {

    private long bookCopyId;

    private long bookId;

    private String location;

    private BookStatus status;

}
