package com.api.application.controller.student;

import com.api.application.core.commons.DomainReturnCode;
import com.api.application.core.domain.dto.student.StudentFilterRequest;
import com.api.application.core.domain.dto.student.StudentRequest;
import com.api.application.core.domain.dto.student.StudentResponse;
import com.api.application.core.service.student.StudentService;
import com.api.application.core.utils.core.responses.DataListResponse;
import com.api.application.core.utils.core.responses.DataResponse;
import com.api.application.core.utils.core.resquests.DataRequest;
import com.api.application.core.utils.core.resquests.FilterRequest;
import com.api.application.core.utils.core.resquests.PaginationRequest;
import com.api.application.core.utils.exeption.ApplicationBusinessException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Operation(
            summary = "List Students",
            description = "List Students"
    )
    @GetMapping(
            value = ""
    )
    public DataListResponse<StudentResponse> list(
            @RequestParam(name = "strSearch", required = false) String strSearch,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortByAttribute", required = false) String sortByAttribute,
            @RequestParam(name = "ascendingOrder", required = false) Boolean ascendingOrder,
            HttpServletResponse servletResponse
    ) throws ApplicationBusinessException {

        DataListResponse<StudentResponse> response;
        FilterRequest<StudentFilterRequest> filter = new FilterRequest<>(strSearch);
        StudentFilterRequest pricingFilterRequest = new StudentFilterRequest();
        filter.setData(pricingFilterRequest);

        PaginationRequest pagination = new PaginationRequest(page, pageSize, sortByAttribute, ascendingOrder);
        response = studentService.list(filter, pagination);
        response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        servletResponse.setStatus(HttpServletResponse.SC_OK);
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
            HttpServletResponse servletResponse
    ) throws ApplicationBusinessException {

        DataResponse<StudentResponse> response;

        response = studentService.getStudentById(id);
        response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        servletResponse.setStatus(HttpServletResponse.SC_OK);
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
            HttpServletResponse servletResponse
    ) throws ApplicationBusinessException {

        DataRequest<StudentRequest> request = new DataRequest<>(bodyRequest);
        DataResponse<StudentResponse> response;

        response = studentService.createStudent(request);
        response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        servletResponse.setStatus(HttpServletResponse.SC_OK);
        return response;
    }

    @Operation(
            summary = "Delete Student",
            description = "Delete a Student"
    )
    @DeleteMapping(value = "/delete/{id}")
    public DataResponse<StudentResponse> delete(
            @PathVariable(value = "id") Long id,
            HttpServletResponse servletResponse
    ) throws ApplicationBusinessException {

        DataResponse<StudentResponse> response;

        response = studentService.deleteStudent(id);
        response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        servletResponse.setStatus(HttpServletResponse.SC_OK);
        return response;
    }

    @Operation(
            summary = "Edit Student",
            description = "Edit a Student"
    )
    @PatchMapping(value = "/edit/{id}")
    public DataResponse<StudentResponse> edit(
            @PathVariable(value = "id") Long id,
            @RequestBody StudentRequest bodyRequest,
            HttpServletResponse servletResponse
    ) throws ApplicationBusinessException {

        DataResponse<StudentResponse> response;

        response = studentService.edit(bodyRequest, id);
        response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        servletResponse.setStatus(HttpServletResponse.SC_OK);
        return response;
    }
}
