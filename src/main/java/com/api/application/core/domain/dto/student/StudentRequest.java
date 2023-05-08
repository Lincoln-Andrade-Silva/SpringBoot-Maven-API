package com.api.application.core.domain.dto.student;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentRequest {
    private Long id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private ClassroomDTO classroom;
}
