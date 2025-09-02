package com.example.demostudent2025.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {


    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 3, max = 100, message = "Email must be between 3 and 100 characters")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Student ID cannot be blank")
    @Size(min = 3, max = 100, message = "Student ID must be between 3 and 100 characters")
    private String studentId;

    @NotBlank(message = "Course cannot be blank")
    @Size(min = 3, max = 100, message = "Course must be between 3 and 100 characters")
    private String course;
}
