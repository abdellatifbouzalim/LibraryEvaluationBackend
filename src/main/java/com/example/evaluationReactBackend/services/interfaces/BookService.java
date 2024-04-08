package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.dto.responses.BookResponse;
import com.example.evaluationReactBackend.entities.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    BookResponse getBookById(Long id);
    List<BookResponse> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Long id);
}
