package com.api.application.core.mapper.classroom;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.domain.entity.Classroom;

public class ClassroomMapper {
    private ClassroomMapper(){}

    public static ClassroomDTO createClassroomDtoFromEntity(Classroom classroom){
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setId(classroom.getId());
        classroomDTO.setClassCode(classroom.getClassCode());

        return classroomDTO;
    }

    public static Classroom createClassroomFromDTO(ClassroomDTO classroomDTO){
        Classroom classroom = new Classroom();
        classroom.setClassCode(classroomDTO.getClassCode());

        return classroom;
    }

    public static Classroom createClassroomFromDTOWithId(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setId(classroomDTO.getId());
        classroom.setClassCode(classroomDTO.getClassCode());

        return classroom;
    }
}
