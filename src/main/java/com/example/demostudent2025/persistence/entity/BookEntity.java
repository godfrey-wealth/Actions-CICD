package com.example.demostudent2025.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "books")
public class BookEntity {

    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    String publisher;
    String year;
}
