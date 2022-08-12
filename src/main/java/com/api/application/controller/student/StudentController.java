package com.api.application.controller.student;

import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.service.student.StudentService;
import com.api.application.utils.core.responses.DataListResponse;
import com.api.application.utils.core.responses.DataResponse;
import com.api.application.utils.core.resquests.DataRequest;
import com.api.application.utils.exeption.ApplicationBusinessException;
import com.api.application.core.commons.DomainReturnCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/student")
public class StudentController {
    private static final String LOW = "Low";
    private static final String MODERATE = "Moderate";
    private static final String HIGH = "High";
    private static final String VERY_HIGH = "Very High";

    @Autowired
    StudentService studentService;

    @Operation(
            summary = "Get Student by Id",
            description = "Get Student by Id"
    )
    @GetMapping(
            value = ""
    )
    public DataListResponse<StudentResponse> list(
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataListResponse<StudentResponse> response = new DataListResponse<>();

        try {
            response = studentService.list(locale);
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
            summary = "Get Student by Id",
            description = "Get Student by Id"
    )
    @GetMapping(
            value = "{id}"
    )
    public DataResponse<StudentResponse> get(
            @PathVariable(value = "id") Long id,
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataResponse<StudentResponse> response = new DataResponse<>();

        try {
            response = studentService.getStudentById(id, locale);
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
            summary = "Create a new Student",
            description = "Create a new Student"
    )
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public DataResponse<StudentResponse> create(
            @RequestBody StudentRequest bodyRequest,
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataRequest<StudentRequest> request = new DataRequest<>(bodyRequest, locale);
        DataResponse<StudentResponse> response = new DataResponse<>();

        try {
            response = studentService.createStudent(request, locale);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);

            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(MODERATE);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;
    }

    @Operation(
            summary = "Delete Student",
            description = "Delete a Student"
    )
    @DeleteMapping(value = "/delete/{id}")
    public DataResponse<StudentResponse> delete(
            @PathVariable(value = "id") Long id,
            @RequestHeader(name = "locale") String locale,
            HttpServletResponse servletResponse
    ) {

        DataResponse<StudentResponse> response = new DataResponse<>();

        try {
            response = studentService.deleteStudent(id, locale);
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
}
