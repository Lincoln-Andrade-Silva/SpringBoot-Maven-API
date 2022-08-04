package com.api.application.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_classroom")
@Where(clause = "deleted=false")
public class Classroom  {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "class_code", nullable = false)
    private String classCode;
}
