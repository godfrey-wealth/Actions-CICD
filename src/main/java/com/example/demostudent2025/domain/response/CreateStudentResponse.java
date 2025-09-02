package com.example.demostudent2025.domain.response;

import com.example.demostudent2025.domain.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentResponse {

    private String id;

    private  String message;

    private StudentDto student;
}
