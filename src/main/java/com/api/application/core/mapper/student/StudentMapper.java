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
        return Student.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .classroom(ClassroomMapper.createClassroomFromDTO(request.getClassroom()))
                .build();
    }

    public static StudentResponse createStudentResponseFromEntity(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .birthDate(student.getBirthDate())
                .classroom(ClassroomMapper.createClassroomDtoFromEntity(student.getClassroom()))
                .name(student.getName())
                .lastName(student.getLastName())
                .build();
    }

    public static void editStudent(Student entity, StudentRequest request) {
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        entity.setClassroom(ClassroomMapper.createClassroomFromDTO(request.getClassroom()));
        entity.setBirthDate(request.getBirthDate());
    }

    public static DataListResponse<StudentResponse> createDataListResponseFromPage(Page<Student> studentsPage) {

        DataListResponse<StudentResponse> dataListResponse = new DataListResponse<>();
        List<StudentResponse> studentResponses = new ArrayList<>();

        for (Student student : studentsPage) {
            studentResponses.add(createStudentResponseFromEntity(student));
        }

        dataListResponse.setTotalData(studentsPage.getTotalElements());
        dataListResponse.setTotalPages(studentsPage.getTotalPages());
        dataListResponse.setData(studentResponses);

        return dataListResponse;
    }
}
