package com.techedgegroup.accademy.course.repository;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Student;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @EntityGraph(attributePaths = { "courses" })
    @Query("select c from Student c")
    List<Student> findAllStudents();
}
