package com.example.demostudent2025.persistence.repository;

import com.example.demostudent2025.persistence.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {

    @Override
    Optional<StudentEntity> findById(String id);

    Optional<StudentEntity> findByStudentId(String studentId);

    boolean existsByStudentId(String studentId);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
