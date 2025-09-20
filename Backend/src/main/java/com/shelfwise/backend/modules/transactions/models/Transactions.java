package com.shelfwise.backend.modules.transactions.models;
import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.books.models.BookCopy;
import com.shelfwise.backend.modules.fine.model.Fine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "bookCopyId"),
        @Index(columnList = "status, dueDate")
})
public class Transactions {
//    Fields: transaction_id (PK), user_id (FK → User), book_copy_id (FK → Book_copy),
//    transaction_type (ENUM: CHECKOUT, RETURN, RENEWAL), checkout_date, due_date,
//    return_date, status (ENUM: ACTIVE, COMPLETED, OVERDUE), created_at.
//    Relationships:
//    Many Transactions → One User.
//    Many Transactions → One Book_copy.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tId;

    @ManyToOne
    @JoinColumn(name= "userId",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="bookCopyId",nullable = false)
    private BookCopy bookCopy;

    @Column(nullable = false)
    private LocalDate checkoutDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = true)
    private LocalDate returnDate;

    @OneToOne(mappedBy = "transaction",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Fine fine;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @CreationTimestamp
    @Column(updatable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}
