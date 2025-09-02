package com.example.demostudent2025.business.impl;


import com.example.demostudent2025.business.interfaces.BookService;
import com.example.demostudent2025.business.mapper.BookMapperDto;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.configuration.exceptions.StudentNotFoundException;
import com.example.demostudent2025.domain.dto.BookDto;
import com.example.demostudent2025.domain.request.CreateBookRequest;
import com.example.demostudent2025.domain.response.CreateBookResponse;
import com.example.demostudent2025.persistence.entity.BookEntity;
import com.example.demostudent2025.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private  final BookRepository bookRepository;

    private final BookMapperDto bookMapperDto;
    @Override
    public CreateBookResponse createBook(CreateBookRequest request) {

        CheckBookExisting(request);

        BookEntity book = bookMapperDto.MapToBookEntity(request);
        bookRepository.save(book);
        return CreateBookResponse.builder()
                .id(book.getId())
                .message("Book Created Successfully")
                .build();


    }

    @Override
    public BookDto getBook(String id) {
        BookEntity book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw  new StudentNotFoundException("Book Not Found: "+id);
        }else{
            return bookMapperDto.MapToBookDto(book);
        }
    }

    @Override
    public BookDto getBookByTitle(String title) {
        BookEntity  book = bookRepository.findByTitle(title).orElse(null);
        if(book == null){
            throw  new StudentNotFoundException("Book Not Found: "+title);
        }else{
            return bookMapperDto.MapToBookDto(book);
        }
    }

    @Override
    public void deleteBook(String id) {

        BookEntity book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw  new StudentNotFoundException("Book Not Found: "+id);
        }else{
            bookRepository.delete(book);
        }

    }

    @Override
    public List<BookDto> getAllBooks() {

        return bookRepository.findAll().stream()
                .map(bookMapperDto::MapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreateBookResponse updateBook(String id, CreateBookRequest request) {

        BookEntity book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw  new StudentNotFoundException("Book Not Found: "+id);
        }else{
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setDescription(request.getDescription());
            book.setPublisher(request.getPublisher());
            book.setYear(request.getYear());
            bookRepository.save(book);
            return CreateBookResponse.builder()
                    .id(book.getId())
                    .message("Book Updated Successfully")
                    .build();
        }
    }


    private  void CheckBookExisting(CreateBookRequest request) {

        if(bookRepository.existsByTitle(request.getTitle())){
            throw  new StudentAlreadyExistsException("Book already exists : "+request.getTitle());
        }

        if(bookRepository.existsByAuthor(request.getAuthor())){
            throw  new StudentAlreadyExistsException("Book already exists : "+request.getAuthor());

        }

        if(bookRepository.existsByPublisher(request.getPublisher())){
            throw  new StudentAlreadyExistsException("Book already exists : "+request.getPublisher());
        }

        if(bookRepository.existsByYear(request.getYear())){
            throw  new StudentAlreadyExistsException("Book already exists : "+request.getYear());
        }
    }
}
