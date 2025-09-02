package com.example.demostudent2025.domain.response;

import com.example.demostudent2025.domain.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateBookResponse {

    private String id;
    private String message;
    private BookDto book;
}
