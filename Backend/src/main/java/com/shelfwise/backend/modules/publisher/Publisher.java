package com.shelfwise.backend.modules.publisher;


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
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long publisherId;

    @Column(nullable = false, length = 50)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
    
}
