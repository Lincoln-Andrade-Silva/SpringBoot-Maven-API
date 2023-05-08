package com.api.application.core.service.classroom;

import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;

public interface IClassroomService {
    DataListResponse<ClassroomDTO> list() throws ApplicationBusinessException;

    DataResponse<ClassroomDTO> getClassroomById(Long id) throws ApplicationBusinessException;

    DataResponse<ClassroomDTO> createClassroom(DataRequest<ClassroomDTO> request) throws ApplicationBusinessException, ApplicationBusinessException;
}
