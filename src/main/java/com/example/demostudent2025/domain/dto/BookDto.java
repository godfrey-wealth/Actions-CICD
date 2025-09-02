package com.example.demostudent2025.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private String author;
    private String description;
    private String publisher;
    private String year;
}
