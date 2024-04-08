package com.example.evaluationReactBackend.controllers;

import com.example.evaluationReactBackend.dto.ApiResponse;
import com.example.evaluationReactBackend.dto.responses.BookResponse;
import com.example.evaluationReactBackend.entities.Book;
import com.example.evaluationReactBackend.exceptions.BookNameExistsException;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.services.interfaces.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveBook(@Valid @RequestBody Book book) {
        try {
            Book savedBook = bookService.saveBook(book);
            ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Book created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BookNameExistsException e) {
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error saving book");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            BookResponse bookResponse = bookService.getBookById(id);
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        try {
            book.setId(id);
            bookService.updateBook(book);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Book updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating book");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
