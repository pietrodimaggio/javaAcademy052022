package com.techedgegroup.accademy.course.repository;

import java.util.List;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @EntityGraph(attributePaths = { "courses" })
	List<Student> findAll();

	@Modifying
	@Query(value = "DELETE FROM COURSE_STUDENT WHERE STUDENT_ID=:idStudente", nativeQuery = true)
	void deleteStudentCourses(@Param("idStudente") Integer id);
	
	
}
