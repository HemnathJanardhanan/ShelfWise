package com.shelfwise.backend.modules.author;


import com.shelfwise.backend.modules.books.models.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;

    @Column(nullable = false)
    private String authorName;

    @ManyToMany(mappedBy="authors")
    private List<Book> books;
}
