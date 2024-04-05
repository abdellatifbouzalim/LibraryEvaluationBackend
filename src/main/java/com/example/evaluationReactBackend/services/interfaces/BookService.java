package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.entities.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Long id);
}
