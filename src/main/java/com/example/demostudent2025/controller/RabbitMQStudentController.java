package com.example.demostudent2025.controller;
import com.example.demostudent2025.business.impl.RabbitMQStudentService;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;

import com.example.demostudent2025.domain.response.CreateStudentResponse;
import com.example.demostudent2025.persistence.entity.StudentEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQStudentController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQStudentController.class);

        private final RabbitMQStudentService studentService;


    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@Valid @RequestBody CreateStudentRequest request) {
        try {
            // Add student using service
            StudentEntity response = studentService.addStudent(request);

            // Return created student with 201 status
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (StudentAlreadyExistsException e) {
            logger.warn("Student already exists: {}", e.getMessage());

            // Return conflict status with error message
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));

        } catch (Exception e) {
            logger.error("Unexpected error: ", e);

            // Return internal server error with generic message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Something went wrong"));
        }
    }


    @PutMapping("/update/{id}")
        public ResponseEntity<?> updateStudent(@PathVariable String id,
                                               @Valid @RequestBody CreateStudentRequest request) {
            StudentEntity updated = studentService.updateStudent(id, request);
            if (updated == null) {
                return ResponseEntity.badRequest().body("Student not found.");
            }
            return ResponseEntity.ok(updated);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteStudent(@PathVariable String id) {
            return ResponseEntity.ok(studentService.deleteStudent(id));
        }

        @GetMapping("/{id}")
        public ResponseEntity<StudentEntity> getStudent(@PathVariable String id) {
            StudentEntity student = studentService.getStudent(id);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        }

    // Endpoint to get all students
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    }


