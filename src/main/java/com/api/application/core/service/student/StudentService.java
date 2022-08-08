package com.api.application.core.service.student;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.mapper.student.StudentMapper;
import com.api.application.core.persistance.repository.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest request){
        Student student = StudentMapper.createStudentFromRequest(request);

        studentRepository.save(student);

        StudentResponse response = StudentMapper.createStudentResponseFromEntity(student);
        return response;
    }

    public StudentResponse deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student entity = optionalStudent.get();
        Student student = StudentMapper.deleteStudent(entity);
        studentRepository.save(student);
        StudentResponse response = StudentMapper.createStudentResponseFromEntity(student);
        return response;
    }
}
