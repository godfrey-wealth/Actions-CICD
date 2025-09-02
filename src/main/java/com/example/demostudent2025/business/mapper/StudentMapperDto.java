package com.example.demostudent2025.business.mapper;

import com.example.demostudent2025.domain.dto.StudentDto;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.persistence.entity.StudentEntity;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperDto {

    public StudentDto MapStudentDto(StudentEntity entity){

        return StudentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .studentId(entity.getStudentId())
                .course(entity.getCourse())
                .build();
    }

    public StudentEntity MapStudentEntity(CreateStudentRequest request){

        return StudentEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .studentId(request.getStudentId())
                .course(request.getCourse())
                .build();
    }
}
