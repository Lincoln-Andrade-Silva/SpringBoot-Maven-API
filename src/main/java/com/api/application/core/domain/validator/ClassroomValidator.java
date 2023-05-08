package com.api.application.core.domain.validator;

import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.domain.entity.Classroom;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Component
public class ClassroomValidator {

    private ClassroomValidator() {
    }

    public static void validateList(List<Classroom> classrooms)
            throws ApplicationBusinessException {

        if (classrooms.isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_NO_CONTENT,
                    DomainReturnCode.CLASSROOM_LIST_ARE_EMPTY.toString(),
                    DomainReturnCode.CLASSROOM_LIST_ARE_EMPTY.getDesc()
            );
        }
    }

    public static Classroom validateOptional(Optional<Classroom> optionalClassroom)
            throws ApplicationBusinessException {

        if (optionalClassroom.isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_NOT_FOUND,
                    DomainReturnCode.CLASSROOM_NOT_FOUND.toString(),
                    DomainReturnCode.CLASSROOM_NOT_FOUND.getDesc()
            );
        } else {
            return optionalClassroom.get();
        }
    }

    public static void validateClassroomDTO(ClassroomDTO request)
            throws ApplicationBusinessException {
        if (request.getClassCode() == null) {
            throw new ApplicationBusinessException(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    DomainReturnCode.CLASSROOM_CODE_IS_NULL.toString(),
                    DomainReturnCode.CLASSROOM_CODE_IS_NULL.getDesc()
            );
        }
    }

    public static void validateNameClassroomExists(List<Classroom> classroomFromDB)
            throws ApplicationBusinessException {

        if (!classroomFromDB.isEmpty()) {
            throw new ApplicationBusinessException(
                    HttpServletResponse.SC_BAD_REQUEST,
                    DomainReturnCode.CLASSROOM_EXISTS.toString(),
                    DomainReturnCode.CLASSROOM_EXISTS.getDesc()
                   );
        }
    }
}
