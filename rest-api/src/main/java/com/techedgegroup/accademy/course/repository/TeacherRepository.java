package com.techedgegroup.accademy.course.repository;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Teacher;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @EntityGraph(attributePaths = { "courses" })
    @Query("select c from Teacher c")
    List<Teacher> findAllTeachers();
}
