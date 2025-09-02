package com.example.demostudent2025.business.interfaces;

import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.domain.response.CreateStudentResponse;

import java.util.List;

public interface StudentService {


    CreateStudentResponse createStudent(CreateStudentRequest request);

    public List<StudentDto> getAllStudents();

    public StudentDto getStudent(String id);

    StudentDto getStudentByStudentId(String studentId);

    public CreateStudentResponse deleteStudent(String id);

   CreateStudentResponse  updateStudent(String id, CreateStudentRequest request);



}
