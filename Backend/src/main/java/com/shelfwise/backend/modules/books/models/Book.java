package com.shelfwise.backend.modules.books.models;


import com.shelfwise.backend.modules.author.Author;
import com.shelfwise.backend.modules.publisher.Publisher;
import com.shelfwise.backend.modules.reservation.models.Reservation;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(
            name="book_authors",
            joinColumns = {@JoinColumn(name="bookId")},
            inverseJoinColumns={@JoinColumn(name="authorId")}
    )
    private List<Author> authors= new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisherId")
    private Publisher publisher;

    @Column(nullable = false)
    private LocalDate publishDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(unique = true, nullable = false)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopy> bookCopies = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
