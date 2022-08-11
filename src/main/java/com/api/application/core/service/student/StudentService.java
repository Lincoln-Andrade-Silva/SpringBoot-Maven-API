package com.api.application.core.service.student;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.domain.validator.StudentValidator;
import com.api.application.core.mapper.student.StudentMapper;
import com.api.application.core.persistance.repository.student.StudentRepository;
import com.api.application.utils.core.responses.DataResponse;
import com.api.application.utils.core.resquests.DataRequest;
import com.api.application.utils.exeption.ApplicationBusinessException;
import com.api.application.core.commons.DomainReturnCode;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final MessageSource messageSource;

    public StudentService(StudentRepository studentRepository, MessageSource messageSource) {
        this.studentRepository = studentRepository;
        this.messageSource = messageSource;
    }

    public DataResponse<StudentResponse> createStudent(DataRequest<StudentRequest> request, String locale)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        StudentValidator.validateStudentRequest(request.getData(), messageSource, locale);
        Student student = StudentMapper.createStudentFromRequest(request.getData());

        studentRepository.save(student);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);

        dataResponse.setData(studentResponse);
        dataResponse.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.name());

        return dataResponse;
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
