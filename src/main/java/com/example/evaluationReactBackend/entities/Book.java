package com.example.evaluationReactBackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min = 3, max = 50, message = "Author must be between 3 and 50 characters")
    private String author;

    @NotEmpty(message = "Genre cannot be empty")
    @Size(min = 3, max = 50, message = "Genre must be between 3 and 50 characters")
    private String genre;

    @NotEmpty(message = "Summary cannot be empty")
    @Size(min = 10, max = 1000, message = "Summary must be between 10 and 1000 characters")
    private String summary;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
