package com.api.application.core.domain.validator;

import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.domain.entity.Classroom;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClassroomValidator {

    private ClassroomValidator() {
    }

    public static void validateList(List<Classroom> classrooms, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {

        if (classrooms.isEmpty()) {

            throw new ApplicationBusinessException(DomainReturnCode.CLASSROOM_LIST_ARE_EMPTY.name(),
                    DomainReturnCode.CLASSROOM_LIST_ARE_EMPTY.getTranslatedDescription(messageSource, locale));
        }
    }

    public static Classroom validateOptional(Optional<Classroom> optionalClassroom, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {

        return optionalClassroom.orElseThrow(() -> new ApplicationBusinessException(DomainReturnCode.CLASSROOM_NOT_FOUND.name(),
                DomainReturnCode.CLASSROOM_NOT_FOUND.getTranslatedDescription(messageSource, locale)));
    }

    public static void validateClassroomDTO(ClassroomDTO request, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {
        if (request.getClassCode() == null) {
            throw new ApplicationBusinessException(DomainReturnCode.CLASSROOM_CODE_IS_NULL.name(),
                    DomainReturnCode.CLASSROOM_CODE_IS_NULL.getTranslatedDescription(messageSource, locale));
        }
    }

    public static void validateNameClassroomExists(List<Classroom> classroomFromDB, MessageSource messageSource, String locale)
            throws ApplicationBusinessException {

        if (!classroomFromDB.isEmpty()) {
            throw new ApplicationBusinessException(DomainReturnCode.CLASSROOM_EXISTS.name(),
                    DomainReturnCode.CLASSROOM_EXISTS.getTranslatedDescription(messageSource, locale));
        }
    }
}
