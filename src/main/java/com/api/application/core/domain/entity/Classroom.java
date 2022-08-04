package com.api.application.core.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_classroom")
@Where(clause = "deleted=false")
public class Classroom  implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
}

