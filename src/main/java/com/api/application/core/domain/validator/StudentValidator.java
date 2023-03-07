package com.api.application.core.domain.validator;

import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentValidator {

    private StudentValidator() {
    }

    public static void validateList(List<Student> studentList)
            throws ApplicationBusinessException {
        if (studentList.isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_NO_CONTENT,
                    DomainReturnCode.STUDENT_LIST_ARE_EMPTY.getDesc()
            );
        }
    }

    public static Student validateOptional(Optional<Student> student)
            throws ApplicationBusinessException {

        return student.orElseThrow(() -> new ApplicationBusinessException(
                HttpServletResponse.SC_NOT_FOUND,
                DomainReturnCode.STUDENT_NOT_FOUND.getDesc()
        ));
    }

    public static void validateStudentRequest(StudentRequest request)
            throws ApplicationBusinessException {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    DomainReturnCode.NAME_IS_NULL.getDesc()
            );
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    DomainReturnCode.LAST_NAME_IS_NULL.getDesc()
            );
        }

        if (request.getClassroom().getId() == null) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    DomainReturnCode.CLASSROOM_ID_IS_NULL.getDesc()
            );
        }

        if (Objects.isNull(request.getBirthDate())) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    DomainReturnCode.BIRTH_DATE_IS_NULL.getDesc()
            );
        }
    }

    public static void validateStudentExists(List<Student> studentFromDB)
            throws ApplicationBusinessException {

        if (!studentFromDB.isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_BAD_REQUEST,
                    DomainReturnCode.STUDENT_EXISTS.getDesc()
            );
        }
    }
}
