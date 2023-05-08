package com.api.application.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_student")
@Where(clause = "deleted=false")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(targetEntity = Classroom.class)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public enum Fields {
        ID("id"), NAME("name"), LASTNAME("lastName"), BIRTHDATE("birthDate"), CLASSROOM("classroom");

        private final String field;

        Fields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }
}
