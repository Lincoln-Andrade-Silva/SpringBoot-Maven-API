package com.api.application.core.domain.validator;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.commons.DomainReturnCode;
import com.api.application.utils.exeption.ApplicationBusinessException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentValidator {

    private StudentValidator() {
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
