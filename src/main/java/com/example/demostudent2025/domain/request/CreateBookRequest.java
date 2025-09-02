package com.example.demostudent2025.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateBookRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 3, max = 100, message = "Author must be between 3 and 100 characters")
    private String author;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
    private String description;

    @NotBlank(message = "Publisher cannot be blank")
    @Size(min = 3, max = 100, message = "Publisher must be between 3 and 100 characters")
    private String publisher;

    @NotBlank(message = "Year cannot be blank")
    @Size(min = 3, max = 100, message = "Year must be between 3 and 100 characters")
    private String year;
}
