package com.api.application.core.domain.validator;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.entity.Student;
import com.api.application.utils.exeption.ApplicationBusinessException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentValidator {

    private StudentValidator() {
    }

    public static void validateList(List<Student> studentList, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {
        if (studentList.isEmpty()) {

            throw new ApplicationBusinessException(DomainReturnCode.STUDENT_LIST_ARE_EMPTY.name(),
                    DomainReturnCode.STUDENT_LIST_ARE_EMPTY.getTranslatedDescription(messageSource, locale));
        }
    }

    public static Student validateOptional(Optional<Student> student, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {

        return student.orElseThrow(() -> new ApplicationBusinessException(DomainReturnCode.STUDENT_NOT_FOUND.name(),
                DomainReturnCode.STUDENT_NOT_FOUND.getTranslatedDescription(messageSource, locale)));
    }

    public static void validateStudentRequest(StudentRequest request, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ApplicationBusinessException(DomainReturnCode.NAME_IS_NULL.name(),
                    DomainReturnCode.NAME_IS_NULL.getTranslatedDescription(messageSource, locale));
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new ApplicationBusinessException(DomainReturnCode.LAST_NAME_IS_NULL.name(),
                    DomainReturnCode.LAST_NAME_IS_NULL.getTranslatedDescription(messageSource, locale));
        }

        if (request.getClassroom().getId() == null) {
            throw new ApplicationBusinessException(DomainReturnCode.CLASSROOM_ID_IS_NULL.name(),
                    DomainReturnCode.CLASSROOM_CODE_IS_NULL.getTranslatedDescription(messageSource, locale));
        }

        if (Objects.isNull(request.getBirthDate())) {
            throw new ApplicationBusinessException(DomainReturnCode.BIRTH_DATE_IS_NULL.name(),
                    DomainReturnCode.BIRTH_DATE_IS_NULL.getTranslatedDescription(messageSource, locale));
        }
    }
}
