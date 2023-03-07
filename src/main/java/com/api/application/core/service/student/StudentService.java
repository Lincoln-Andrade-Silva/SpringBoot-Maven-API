package com.api.application.core.service.student;

import com.api.application.core.domain.dto.student.StudentFilterRequest;
import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Classroom;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.domain.validator.ClassroomValidator;
import com.api.application.core.domain.validator.StudentValidator;
import com.api.application.core.mapper.student.StudentMapper;
import com.api.application.core.persistance.repository.classroom.ClassroomRepository;
import com.api.application.core.persistance.repository.student.StudentCriteriaRepository;
import com.api.application.core.persistance.repository.student.StudentRepository;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.core.resquests.FilterRequest;
import com.api.application.core.utils.core.resquests.PaginationRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final ClassroomRepository classroomRepository;

    private final StudentCriteriaRepository studentCriteriaRepository;

    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository,
                          StudentCriteriaRepository studentCriteriaRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
        this.studentCriteriaRepository = studentCriteriaRepository;
    }


    public DataListResponse<StudentResponse> list(FilterRequest<StudentFilterRequest> filter,
                                                  PaginationRequest pagination) throws ApplicationBusinessException {
        Page<Student> pricingListPage = studentCriteriaRepository.findAllWithFilters(pagination, filter);

        return StudentMapper.createDataListResponseFromPage(pricingListPage);
    }

    public DataResponse<StudentResponse> getStudentById(Long id) throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = StudentValidator.validateOptional(optionalStudent);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);
        dataResponse.setData(studentResponse);

        return dataResponse;
    }

    public DataResponse<StudentResponse> createStudent(DataRequest<StudentRequest> request)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        StudentValidator.validateStudentRequest(request.getData());

        List<Student> studentFromDB = studentRepository.findByStudent(request.getData().getName(), request.getData().getLastName());
        StudentValidator.validateStudentExists(studentFromDB);

        Optional<Classroom> classroomFromDB = classroomRepository.findById(request.getData().getClassroom().getId());
        ClassroomValidator.validateOptional(classroomFromDB);

        Student student = StudentMapper.createStudentFromRequest(request.getData());
        studentRepository.save(student);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(student);
        dataResponse.setData(studentResponse);

        return dataResponse;
    }

    public DataResponse<StudentResponse> deleteStudent(Long id)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student entity = StudentValidator.validateOptional(optionalStudent);
        Student student = StudentMapper.deleteStudent(entity);
        studentRepository.save(student);
        StudentResponse response = StudentMapper.createStudentResponseFromEntity(student);

        dataResponse.setData(response);

        return dataResponse;
    }

    public DataResponse<StudentResponse> edit(StudentRequest request, Long id)
            throws ApplicationBusinessException {

        DataResponse<StudentResponse> dataResponse = new DataResponse<>();

        StudentValidator.validateStudentRequest(request);

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student entity = StudentValidator.validateOptional(optionalStudent);

        StudentMapper.editStudent(entity, request);
        studentRepository.save(entity);

        StudentResponse studentResponse = StudentMapper.createStudentResponseFromEntity(entity);
        dataResponse.setData(studentResponse);

        return dataResponse;
    }
}
