package com.api.application.core.domain.dto.classroom;

import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.domain.entity.Student;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private Long id;

    private String classCode;

    private List<StudentResponse> studentsList;
}
