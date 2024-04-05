package com.example.evaluationReactBackend.entities;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int rating;
    private String date;

    // Relation Many-to-One avec Livre (Book)
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Relation Many-to-One avec Utilisateur (User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
