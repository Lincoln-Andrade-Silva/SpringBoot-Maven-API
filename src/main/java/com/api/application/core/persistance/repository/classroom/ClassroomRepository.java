package com.api.application.core.persistance.repository.classroom;

import com.api.application.core.domain.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Query("select p from Classroom p where lower(trim(p.classCode)) = lower(trim(:classCode)) and p.deleted = false")
    List<Classroom> findByClassCode(@Param("classCode")String classCode);
}
