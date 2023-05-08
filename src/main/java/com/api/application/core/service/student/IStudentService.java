package com.api.application.core.service.student;

import com.api.application.core.domain.dto.student.StudentFilterRequest;
import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.core.resquests.FilterRequest;
import com.api.application.core.utils.core.resquests.PaginationRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;

public interface IStudentService {
    DataListResponse<StudentResponse> list(FilterRequest<StudentFilterRequest> filter,
                                           PaginationRequest pagination) throws ApplicationBusinessException;

    DataResponse<StudentResponse> getStudentById(Long id) throws ApplicationBusinessException;

    DataResponse<StudentResponse> createStudent(DataRequest<StudentRequest> request) throws ApplicationBusinessException, ApplicationBusinessException;

    DataResponse<StudentResponse> deleteStudent(Long id) throws ApplicationBusinessException;

    DataResponse<StudentResponse> edit(StudentRequest request, Long id) throws ApplicationBusinessException;
}
