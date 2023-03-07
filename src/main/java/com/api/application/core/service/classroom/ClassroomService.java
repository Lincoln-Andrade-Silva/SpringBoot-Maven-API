package com.api.application.core.service.classroom;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.domain.entity.Classroom;
import com.api.application.core.domain.validator.ClassroomValidator;
import com.api.application.core.mapper.classroom.ClassroomMapper;
import com.api.application.core.persistance.repository.classroom.ClassroomRepository;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public DataListResponse<ClassroomDTO> list() throws ApplicationBusinessException {
        DataListResponse<ClassroomDTO> dataResponses = new DataListResponse<>();
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();

        List<Classroom> classrooms = classroomRepository.findAll();
        ClassroomValidator.validateList(classrooms);

        for (Classroom classroom : classrooms) {
            ClassroomDTO classroomDTO = ClassroomMapper.createClassroomDtoFromEntity(classroom);
            classroomDTOS.add(classroomDTO);
        }

        dataResponses.setData(classroomDTOS);

        return dataResponses;
    }

    public DataResponse<ClassroomDTO> getClassroomById(Long id) throws ApplicationBusinessException {

        DataResponse<ClassroomDTO> dataResponse = new DataResponse<>();

        Optional<Classroom> optionalClassroom = classroomRepository.findById(id);
        Classroom classroom = ClassroomValidator.validateOptional(optionalClassroom);

        ClassroomDTO classroomDTO = ClassroomMapper.createClassroomDtoFromEntity(classroom);
        dataResponse.setData(classroomDTO);

        return dataResponse;
    }

    public DataResponse<ClassroomDTO> createClassroom(DataRequest<ClassroomDTO> request)
            throws ApplicationBusinessException {

        DataResponse<ClassroomDTO> dataResponse = new DataResponse<>();

        ClassroomValidator.validateClassroomDTO(request.getData());

        List<Classroom> classroomFromDB = classroomRepository.findByClassCode(request.getData().getClassCode());
        ClassroomValidator.validateNameClassroomExists(classroomFromDB);

        Classroom classroom = ClassroomMapper.createClassroomFromDTO(request.getData());

        classroomRepository.save(classroom);

        ClassroomDTO classroomDTO = ClassroomMapper.createClassroomDtoFromEntity(classroom);
        dataResponse.setData(classroomDTO);

        return dataResponse;
    }
}
