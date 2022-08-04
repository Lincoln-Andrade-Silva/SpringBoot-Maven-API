package com.api.application.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private Long id;

    private String name;

    private String lastName;

    private Date birthDate;

    private String classroom;
}
