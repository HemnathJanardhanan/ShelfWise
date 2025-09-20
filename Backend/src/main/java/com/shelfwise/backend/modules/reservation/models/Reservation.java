package com.shelfwise.backend.modules.reservation.models;


import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.books.models.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
//    Fields: reservation_id (PK), user_id (FK → User), book_id (FK → Book), reservation_date, expiration_date,
//    status (ENUM: PENDING, CONFIRMED, CANCELLED, EXPIRED).
//    Relationships:
//    Many Reservations → One User.
//    Many Reservations → One Book.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private ReservationStatus status;

}
