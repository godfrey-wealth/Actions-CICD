package com.example.demostudent2025.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class StudentDto {

    private String id;
    private String name;
    private String email;
    private String studentId;
    private String course;
}
