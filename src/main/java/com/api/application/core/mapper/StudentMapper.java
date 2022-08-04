package com.api.application.core.mapper;

import com.api.application.core.domain.dto.StudentRequest;
import com.api.application.core.domain.dto.StudentResponse;
import com.api.application.core.domain.entity.Student;

public class StudentMapper {
    private StudentMapper(){
    }

    public static Student createStudentFromRequest(StudentRequest request){
        Student student =  new Student();
        student.setName(request.getName());
        student.setLastName(request.getLastName());
        student.setBirthDate(request.getBirthDate());
        student.setClassroom(request.getClassroom());

        return student;
    }

    public static StudentResponse createStudentResponseFromEntity(Student student){
        StudentResponse response =  new StudentResponse();
        response.setName(student.getName());
        response.setLastName(student.getLastName());
        response.setBirthDate(student.getBirthDate());
        response.setClassroom(student.getClassroom());

        return response;
    }
}
