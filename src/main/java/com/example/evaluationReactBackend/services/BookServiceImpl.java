package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.entities.Book;
import com.example.evaluationReactBackend.exceptions.BookNameExistsException;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.repositories.BookRepository;
import com.example.evaluationReactBackend.services.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        String bookName = book.getTitle(); // Assuming title is the name of the book

        // Check if a book with the same name already exists
        if (bookRepository.existsByTitle(bookName)) {
            throw new BookNameExistsException("A book with the name '" + bookName + "' already exists.");
        }

        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Error saving book", e);
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook( Book updatedBook) {
        Book existingBook = bookRepository.findById(updatedBook.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + updatedBook.getId()));

        // Update the fields of the existing book with the new values
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setSummary(updatedBook.getSummary());
        // Update other fields as needed

        // Save the updated book to the database
        bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book not found with ID: " + id);
        }
    }
}
