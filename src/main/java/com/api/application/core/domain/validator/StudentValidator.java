package com.api.application.core.domain.validator;

import com.api.application.core.domain.dto.student.StudentRequest;
import org.springframework.context.ApplicationContextException;

public class StudentValidator {

    public void validateToCreate(StudentRequest request)
            throws ApplicationContextException {
        if (request.getName().isEmpty()) {

        }
    }
}
