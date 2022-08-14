package com.api.application.controller.classroom;

import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.dto.classroom.ClassroomDTO;
import com.api.application.core.service.classroom.ClassroomService;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/classroom")
public class ClassroomController {

    private static final String LOW = "Low";
    private static final String MODERATE = "Moderate";
    private static final String VERY_HIGH = "Very High";

    @Autowired
    ClassroomService classroomService;

    @Operation(
            summary = "List Classrooms",
            description = "List Classrooms"
    )
    @GetMapping(
            value = ""
    )
    public DataListResponse<ClassroomDTO> list(
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataListResponse<ClassroomDTO> response = new DataListResponse<>();

        try {
            response = classroomService.list(locale);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(MODERATE);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }


    @Operation(
            summary = "Get Classroom by Id",
            description = "Get Classroom by Id"
    )
    @GetMapping(
            value = "{id}"
    )
    public DataResponse<ClassroomDTO> get(
            @PathVariable(value = "id") Long id,
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataResponse<ClassroomDTO> response = new DataResponse<>();

        try {
            response = classroomService.getClassroomById(id, locale);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(LOW);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }

    @Operation(
            summary = "Create a new Classroom",
            description = "Create a new Classroom"
    )
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public DataResponse<ClassroomDTO> create(
            @RequestBody ClassroomDTO bodyRequest,
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataRequest<ClassroomDTO> request = new DataRequest<>(bodyRequest, locale);
        DataResponse<ClassroomDTO> response = new DataResponse<>();

        try {
            response = classroomService.createClassroom(request, locale);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);

            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(VERY_HIGH);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return response;
        }
    }
}
