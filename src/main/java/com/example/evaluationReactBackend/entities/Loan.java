package com.example.evaluationReactBackend.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Borrow date cannot be null")
    private LocalDate borrowDate;

    // Relation Many-to-One avec Livre (Book)
    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull(message = "Book cannot be null")
    private Book book;

    // Relation Many-to-One avec Utilisateur (User) pour l'emprunteur
    @ManyToOne
    @JoinColumn(name = "borrowing_user_id")
    @NotNull(message = "Borrowing user cannot be null")
    private User borrowingUser;
}
