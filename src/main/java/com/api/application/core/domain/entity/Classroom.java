package com.api.application.core.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_classroom")
@Where(clause = "deleted=false")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public enum Fields {
        ID("id"), CLASS_CODE("classCode");

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

