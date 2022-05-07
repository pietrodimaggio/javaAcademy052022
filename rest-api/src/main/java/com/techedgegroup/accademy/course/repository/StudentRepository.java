package com.techedgegroup.accademy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("select s from Student s join fetch s.courses")
	List<Student> findAllStudents();

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM COURSE_STUDENT WHERE STUDENT_ID =:id")
	int deleteStudentCourses(@Param("id") Integer studentId);

}
