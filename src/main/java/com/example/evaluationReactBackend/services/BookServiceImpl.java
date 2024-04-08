package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.dto.responses.BookResponse;
import com.example.evaluationReactBackend.entities.Book;
import com.example.evaluationReactBackend.exceptions.BookNameExistsException;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.mappers.BookMapper;
import com.example.evaluationReactBackend.mappers.LoanMapper;
import com.example.evaluationReactBackend.repositories.BookRepository;
import com.example.evaluationReactBackend.services.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        String bookName = book.getTitle();

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
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));
        return BookMapper.INSTANCE.toDto(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBook( Book updatedBook) {
        Book existingBook = bookRepository.findById(updatedBook.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + updatedBook.getId()));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setSummary(updatedBook.getSummary());

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
