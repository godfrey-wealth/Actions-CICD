package com.example.demostudent2025.controller;

import com.example.demostudent2025.business.interfaces.StudentService;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.configuration.exceptions.StudentNotFoundException;
import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.domain.response.CreateStudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;


    // create student

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody @Valid CreateStudentRequest request) {

        try {
            CreateStudentResponse response = studentService.createStudent(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (StudentAlreadyExistsException e) {
            logger.warn("Student already exists: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    // get all students

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // get student
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String id) {
        try {
            return ResponseEntity.ok(studentService.getStudent(id));
        } catch (StudentNotFoundException e) {
            logger.warn("Student not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
    // delete student

    @DeleteMapping("/{id}")
    public ResponseEntity<CreateStudentResponse> deleteStudent(@PathVariable String id) {
        try {
            return ResponseEntity.ok(studentService.deleteStudent(id));
        } catch (StudentNotFoundException e) {
            logger.warn("Student not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CreateStudentResponse.builder().message(e.getMessage()).build());
        }
    }
    // update student

    @PutMapping("/{id}")
    public ResponseEntity<CreateStudentResponse> updateStudent(@PathVariable String id, @RequestBody @Valid CreateStudentRequest request) {

        try {
            return ResponseEntity.ok(studentService.updateStudent(id, request));
        } catch (StudentNotFoundException e) {
            logger.warn("Student not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CreateStudentResponse.builder().message(e.getMessage()).build());

        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CreateStudentResponse.builder().message("Something went wrong").build());
        }
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<StudentDto> getStudentByStudentId(@PathVariable String studentId) {
        try {
            return ResponseEntity.ok(studentService.getStudentByStudentId(studentId));
        } catch (StudentNotFoundException e) {
            logger.warn("Student not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}