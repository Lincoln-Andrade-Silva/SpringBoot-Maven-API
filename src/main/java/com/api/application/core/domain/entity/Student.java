package com.api.application.core.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_student")
@Where(clause = "deleted=false")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="birth_date", nullable = false)
    private Date birthDate;

    @ManyToOne(targetEntity = Classroom.class)
    @JoinColumn(name="classroom_id", nullable = false)
    private Classroom classroom;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
}
