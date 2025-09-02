package com.example.demostudent2025.business.impl;

import com.example.demostudent2025.business.mapper.StudentMapperDto;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.persistence.entity.StudentEntity;
import com.example.demostudent2025.persistence.repository.StudentRepository;
import com.example.demostudent2025.producer.StudentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RabbitMQStudentService {

    private final StudentProducer producer;
    private final StudentRepository repository;


    private  final StudentMapperDto StudentMapperDto;


    public StudentEntity addStudent(CreateStudentRequest request) {

        // check if student already exists

        CheckStudentIdExists(request);

        StudentEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .studentId(request.getStudentId())
                .course(request.getCourse())
                .build();

        StudentEntity saved = repository.save(StudentEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .studentId(request.getStudentId())
                .course(request.getCourse())
                .build());
        producer.sendMessage(saved, "Added");
        return saved;
    }


    // get all students
    // Get all students
    public List<StudentDto> getAllStudents() {
        return repository.findAll().stream()
                .map(StudentMapperDto::MapStudentDto)
                .collect(Collectors.toList());
    }

    public StudentEntity updateStudent(String id, CreateStudentRequest request) {
        Optional<StudentEntity> existing = repository.findById(id);
        if (existing.isPresent()) {
            StudentEntity student = existing.get();
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            student.setStudentId(request.getStudentId());
            student.setCourse(request.getCourse());

            StudentEntity updated = repository.save(student);
            producer.sendMessage(updated, "Updated");
            return updated;
        }
        return null;
    }

    public String deleteStudent(String id) {
        Optional<StudentEntity> student = repository.findById(id);
        if (student.isPresent()) {
            repository.deleteById(id);
            producer.sendMessage(student.get(), "Deleted");
            return "Student with ID " + id + " deleted.";
        }
        return "Student not found.";
    }

    public StudentEntity getStudent(String id) {
        return repository.findById(id).orElse(null);
    }


    private  void CheckStudentIdExists(CreateStudentRequest request){

        if(repository.existsByStudentId(request.getStudentId())){

            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getStudentId());
        }
        if(repository.existsByEmail(request.getEmail())){
            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getEmail());
        }

        if(repository.existsByName(request.getName())){
            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getName());
        }
    }

}
