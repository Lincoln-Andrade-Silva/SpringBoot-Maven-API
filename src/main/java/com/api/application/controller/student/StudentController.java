package com.api.application.controller.student;

import com.api.application.core.domain.dto.StudentRequest;
import com.api.application.core.domain.dto.StudentResponse;
import com.api.application.core.service.StudentService;
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
            summary = "Create a new Student",
            description = "Create a new Student"

    )
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public StudentResponse create(
            @RequestBody StudentRequest request,
            HttpServletResponse servletResponse
    ){
        StudentResponse response = new StudentResponse();
        try {
            response = studentService.createStudent(request);
        }catch (Exception ex) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;
    }
}
