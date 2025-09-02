package com.example.demostudent2025.business.mapper;

import com.example.demostudent2025.domain.dto.BookDto;
import com.example.demostudent2025.domain.request.CreateBookRequest;
import com.example.demostudent2025.persistence.entity.BookEntity;
import org.springframework.stereotype.Component;


@Component
public class BookMapperDto {

    public BookDto MapToBookDto(BookEntity bookEntity){

        return BookDto.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .publisher(bookEntity.getPublisher())
                .year(bookEntity.getYear())
                .build();
    }


    public BookEntity MapToBookEntity(CreateBookRequest createBookRequest){
        return BookEntity.builder()
                .title(createBookRequest.getTitle())
                .author(createBookRequest.getAuthor())
                .description(createBookRequest.getDescription())
                .publisher(createBookRequest.getPublisher())
                .year(createBookRequest.getYear())
                .build();
    }
}
