package com.example.demostudent2025.business.impl;

import com.example.demostudent2025.business.interfaces.StudentService;
import com.example.demostudent2025.business.mapper.StudentMapperDto;
import com.example.demostudent2025.configuration.exceptions.StudentAlreadyExistsException;
import com.example.demostudent2025.configuration.exceptions.StudentNotFoundException;
import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.domain.response.CreateStudentResponse;
import com.example.demostudent2025.persistence.entity.StudentEntity;
import com.example.demostudent2025.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl  implements StudentService {

    private final StudentRepository StudentRepository;

    private  final StudentMapperDto StudentMapperDto;
    @Override
    public CreateStudentResponse createStudent(CreateStudentRequest request) {

        // chck if student already exists
        CheckStudentIdExists(request);

        StudentEntity student = StudentMapperDto.MapStudentEntity(request);
        StudentRepository.save(student);

        return CreateStudentResponse.builder()
                .id(student.getId())
                .message("Student Created Successfully")
                .build();
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return  StudentRepository.findAll().stream()
                .map(StudentMapperDto::MapStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudent(String id) {
        StudentEntity student = StudentRepository.findById(id).orElse(null);

        if(student == null){
            throw  new StudentNotFoundException("Student Not Found: "+id);
        }else{
            return StudentMapperDto.MapStudentDto(student);
        }
    }

    @Override
    public StudentDto getStudentByStudentId(String studentId) {
        StudentEntity  student = StudentRepository.findByStudentId(studentId).orElse(null);
        if(student == null){
            throw  new StudentNotFoundException("Student Not Found: "+studentId);
        }else{
            return StudentMapperDto.MapStudentDto(student);
        }
    }

    @Override
    public CreateStudentResponse deleteStudent(String id) {

        StudentEntity student = StudentRepository.findById(id).orElse(null);

        if(student == null){
            throw  new StudentNotFoundException("Student Not Found: "+id);
        }
        else {
            StudentRepository.delete(student);
            return CreateStudentResponse.builder()
                    .id(student.getId())
                    .message("Student Deleted Successfully")
                    .build();
        }


    }

    @Override
    public CreateStudentResponse updateStudent(String id, CreateStudentRequest request) {

        StudentEntity student = StudentRepository.findById(id).orElse(null);

        if(student == null){
            throw  new StudentNotFoundException("Student Not Found: "+id);
        }
        else {
            // Update Student
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            student.setStudentId(request.getStudentId());
            student.setCourse(request.getCourse());

            StudentRepository.save(student);

            return CreateStudentResponse.builder()
                    .id(student.getId())
                    .message("Student Updated Successfully")
                    .student(StudentMapperDto.MapStudentDto(student))
                    .build();



        }

    }

    private  void CheckStudentIdExists(CreateStudentRequest request){

        if(StudentRepository.existsByStudentId(request.getStudentId())){

            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getStudentId());
        }
        if(StudentRepository.existsByEmail(request.getEmail())){
            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getEmail());
        }

        if(StudentRepository.existsByName(request.getName())){
            throw new StudentAlreadyExistsException("Student Already Exists: "+request.getName());
        }
    }
}
