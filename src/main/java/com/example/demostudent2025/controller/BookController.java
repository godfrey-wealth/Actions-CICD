package com.example.demostudent2025.controller;

import com.example.demostudent2025.business.interfaces.BookService;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.configuration.exceptions.StudentNotFoundException;
import com.example.demostudent2025.domain.dto.BookDto;
import com.example.demostudent2025.domain.request.CreateBookRequest;
import com.example.demostudent2025.domain.response.CreateBookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/books")
@RestController

public class BookController {

    private  final BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    // create book

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookRequest request) {

        try {

            CreateBookResponse response = bookService.createBook(request);

            return new ResponseEntity<>(response, HttpStatus.CREATED);


        } catch (StudentAlreadyExistsException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable String id) {

        try {
            return ResponseEntity.ok(bookService.getBook(id));
        } catch (StudentNotFoundException e) {
            logger.warn("Student not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (StudentNotFoundException e) {
            logger.warn("Book not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody @Valid CreateBookRequest request) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, request));
        } catch (StudentNotFoundException e) {
            logger.warn("Book not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<BookDto> getBookByTitle(@PathVariable String title) {
        try {
            BookDto book = bookService.getBookByTitle(title);
            return ResponseEntity.ok(book);
        } catch (StudentNotFoundException e) {
            logger.warn("Book not found with title '{}': {}", title, e.getMessage());
            // Optionally return a custom error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

}
