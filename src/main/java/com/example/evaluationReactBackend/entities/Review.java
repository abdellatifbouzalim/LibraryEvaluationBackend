package com.example.evaluationReactBackend.entities;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int stars;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
