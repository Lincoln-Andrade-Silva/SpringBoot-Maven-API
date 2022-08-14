package com.api.application.core.domain.dto.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StudentFilterRequest implements Serializable {

    private List<Long> ids;
}
