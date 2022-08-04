package com.api.application.core.domain.dto.student;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;

    private String name;

    private String lastName;

    private Date birthDate;

    private ClassroomDTO classroom;
}
