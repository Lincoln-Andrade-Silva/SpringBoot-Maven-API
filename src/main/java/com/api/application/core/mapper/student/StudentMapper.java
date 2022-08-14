package com.api.application.core.mapper.student;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.mapper.classroom.ClassroomMapper;
import com.api.application.core.utils.core.responses.DataListResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    private StudentMapper() {
    }

    public static Student deleteStudent(Student student) {
        student.setDeleted(Boolean.TRUE);

        return student;
    }

    public static Student createStudentFromRequest(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setLastName(request.getLastName());
        student.setBirthDate(request.getBirthDate());
        student.setClassroom(ClassroomMapper.createClassroomFromDTOWithId(request.getClassroom()));

        return student;
    }

    public static Student createStudentFromResponse(StudentResponse response) {
        Student student = new Student();
        student.setName(response.getName());
        student.setLastName(response.getLastName());
        student.setBirthDate(response.getBirthDate());
        student.setClassroom(ClassroomMapper
                .createClassroomFromDTO(response.getClassroom()));

        return student;
    }

    public static StudentResponse createStudentResponseFromEntity(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setLastName(student.getLastName());
        response.setBirthDate(student.getBirthDate());
        response.setClassroom(ClassroomMapper
                .createClassroomDtoFromEntity(student.getClassroom()));

        return response;
    }

    public static Student editStudent(Student entity, StudentRequest request) {
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        entity.setClassroom(ClassroomMapper.createClassroomFromDTO(request.getClassroom()));
        entity.setBirthDate(request.getBirthDate());

        return entity;
    }

    public static DataListResponse<StudentResponse> createDataListResponseFromPage(Page<Student> studentsPage) {

        DataListResponse<StudentResponse> dataListResponse = new DataListResponse<>();
        List<StudentResponse> studentResponses = new ArrayList<>();

        for (Student student : studentsPage) {
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            studentResponse.setLastName(student.getLastName());
            studentResponse.setBirthDate(student.getBirthDate());
            studentResponse.setClassroom(ClassroomMapper.createClassroomDtoFromEntity(student.getClassroom()));

            studentResponses.add(studentResponse);
        }

        dataListResponse.setTotalData(studentsPage.getTotalElements());
        dataListResponse.setTotalPages(studentsPage.getTotalPages());
        dataListResponse.setData(studentResponses);

        return dataListResponse;
    }
}
