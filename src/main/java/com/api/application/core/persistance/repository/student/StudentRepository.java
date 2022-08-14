package com.api.application.core.persistance.repository.student;

import com.api.application.core.domain.entity.Classroom;
import com.api.application.core.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where lower(trim(s.name)) like lower(trim(:name)) and " +
            "lower(trim(s.lastName)) like lower(trim(:lastName)) and s.deleted = false")
    List<Student> findByStudent(@Param("name") String name, @Param("lastName") String lastName);
}
