package com.api.application.core.mapper.classroom;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.domain.entity.Classroom;

public class ClassroomMapper {
    private ClassroomMapper() {
    }

    public static ClassroomDTO createClassroomDtoFromEntity(Classroom classroom) {
        return ClassroomDTO.builder()
                .id(classroom.getId())
                .classCode(classroom.getClassCode())
                .build();
    }

    public static Classroom createClassroomFromDTO(ClassroomDTO classroomDTO) {
        return Classroom.builder().id(classroomDTO.getId() == null ? null : classroomDTO.getId())
                .classCode(classroomDTO.getClassCode()).build();
    }
}
