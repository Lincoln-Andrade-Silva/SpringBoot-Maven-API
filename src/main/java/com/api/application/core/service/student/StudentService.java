package com.api.application.core.service.student;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.domain.validator.StudentValidator;
import com.api.application.core.mapper.student.StudentMapper;
import com.api.application.core.persistance.repository.student.StudentRepository;
import com.api.application.utils.core.responses.DataListResponse;
import com.api.application.utils.core.responses.DataResponse;
import com.api.application.utils.core.resquests.DataRequest;
import com.api.application.utils.exeption.ApplicationBusinessException;
import com.api.application.core.commons.DomainReturnCode;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final MessageSource messageSource;

    public StudentService(StudentRepository studentRepository, MessageSource messageSource) {
        this.studentRepository = studentRepository;
        this.messageSource = messageSource;
    }


    public DataListResponse<StudentResponse> list(String locale) throws ApplicationBusinessException {
        DataListResponse<StudentResponse> dataResponses = new DataListResponse<>();
        List<StudentResponse> studentResponses = new ArrayList<>();

        List<Student> studentList = studentRepository.findAll();
        StudentValidator.validateList(studentList, messageSource, locale);

        for (Student student : studentList) {
            StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);
            studentResponses.add(studentResponse);
        }

        dataResponses.setData(studentResponses);

        return dataResponses;
    }

    public DataResponse<StudentResponse> getStudentById(Long id, String locale) throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = StudentValidator.validateOptional(optionalStudent, messageSource, locale);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);

        dataResponse.setData(studentResponse);

        return dataResponse;
    }

    public DataResponse<StudentResponse> createStudent(DataRequest<StudentRequest> request, String locale)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        StudentValidator.validateStudentRequest(request.getData(), messageSource, locale);
        Student student = StudentMapper.createStudentFromRequest(request.getData());

        studentRepository.save(student);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);

        dataResponse.setData(studentResponse);

        return dataResponse;
    }

    public DataResponse<StudentResponse> deleteStudent(Long id, String locale)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student entity = StudentValidator.validateOptional(optionalStudent, messageSource, locale);
        Student student = StudentMapper.deleteStudent(entity);
        studentRepository.save(student);
        StudentResponse response = StudentMapper.createStudentResponseFromEntity(student);

        dataResponse.setData(response);

        return dataResponse;
    }
}
