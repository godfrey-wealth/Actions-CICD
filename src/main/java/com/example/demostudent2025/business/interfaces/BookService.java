package com.example.demostudent2025.business.interfaces;

import com.example.demostudent2025.domain.dto.BookDto;
import com.example.demostudent2025.domain.request.CreateBookRequest;
import com.example.demostudent2025.domain.response.CreateBookResponse;

import java.util.List;

public interface BookService {

    CreateBookResponse createBook(CreateBookRequest request);

    BookDto getBook(String id);

    BookDto getBookByTitle(String title);

    void deleteBook(String id);

    List<BookDto> getAllBooks();

    CreateBookResponse updateBook(String id, CreateBookRequest request);

}
